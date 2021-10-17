import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Magazine } from 'src/app/modules/Magazine/Magazine.module';
import { SubscriptionMag } from 'src/app/modules/Magazine/Subscription.module';
import { SubscriptionMessage } from 'src/app/modules/Messages/SubscriptionMessage.module';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';
import { SubscriptionService } from 'src/app/services/Magazine/Subscription.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-magazine-list',
  templateUrl: './magazine-list.component.html',
  styleUrls: ['./magazine-list.component.css'],
})
export class MagazineListComponent implements OnInit {
  public _magsToShow: Array<Magazine>;
  public _user: User;
  public _actualPage: number;
  public _offset: number;
  public _limit: number;
  public _finishPage: number = 5;
  public _showScrollHeight: number = 400;
  public _hideScrollHeight: number = 200;
  public _magToBuy: Magazine;
  public _showGoUpButton: boolean;
  public _showALertMsg: boolean = false;
  public _showSuccessMsg = false;
  public _successMsg: string;
  public _alertMsg: string;
  public _previewMagUrl: string = Routes.PUBLIC_PREVIEW_MAGAZINE;
  public _buySubForm: FormGroup;

  constructor(
    private _magService: MagazineService,
    private _storageService: LocalStorageService,
    private _subscriptionService: SubscriptionService
  ) {
    this._actualPage = 1;
    this._offset = 0;
    this._limit = 10;
    this._actualPage = 1;
    this._showGoUpButton = false;
    this._buySubForm = this.generateFormGroup();
  }

  ngOnInit(): void {
    this._magsToShow = new Array<Magazine>();
    // this.add40Lines();
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this.getMagazines();
    this._magToBuy = this.newEmptyMag();
  }

  onScroll() {
    if (this._actualPage < this._finishPage) {
      this.getMagazines();
      this._actualPage++;
    } else {
      console.log('No more lines. Finish page!');
    }
  }

  private generateFormGroup(): FormGroup {
    return new FormGroup({
      _months: new FormControl('NO_VALID', [Validators.required]),
      _date: new FormControl('', [Validators.required]),
    });
  }

  private getMagazines() {
    this._magService
      .getMagazineForUser(this._limit, this._offset, this._user.email)
      .subscribe(
        (_success: Magazine[]) => {
          for (const _mag of _success) {
            this._magsToShow.push(_mag);
          }
          this._offset += this._limit;
        },
        (_error: Error) => {
          console.log('ERROR');
          console.log(_error);
        }
      );
  }

  scrollTop() {
    document.body.scrollTop = 0; // Safari
    document.documentElement.scrollTop = 0; // Other
  }

  public showBuyMag(_mag: Magazine) {
    this._showALertMsg = false;
    this._showSuccessMsg = false;
    this._magToBuy = _mag;
  }

  public proceedPayment() {
    if (this._buySubForm.valid && this.isMonthValid()) {
      // calc the number of months
      let months: number =
        this._buySubForm.controls['_months'].value === 'yearly' ? 12 : 1;
      // cast dates to string
      let dateTmp = new Date(this._buySubForm.controls['_date'].value);
      let endDate = new Date(
        new Date(dateTmp).setMonth(dateTmp.getMonth() + +months)
      )
        .toISOString()
        .slice(0, 10);
      // create objetct to send as JSON
      let _subscriptn = new SubscriptionMag(
        0,
        months,
        endDate,
        this._buySubForm.controls['_date'].value,
        this._magToBuy.name,
        this._user.email
      );
      // call httpRequest
      this.registPayment(_subscriptn);
    } else {
      this._showALertMsg = true;
      this._alertMsg = 'Los datos ingresados son invalidos';
    }
  }

  private isMonthValid(): boolean {
    return (
      this._buySubForm.controls['_months'].value === 'yearly' ||
      this._buySubForm.controls['_months'].value === 'monthly'
    );
  }

  private registPayment(_subscription: SubscriptionMag): void {
    this._subscriptionService
      .registNewSub(_subscription)
      .subscribe((_success: SubscriptionMessage) => {
        if (_success.message === 'NO_ERROR') {
          this._showSuccessMsg = true;
          this._successMsg =
            'Se ha aprovado el pago y se te ha entregado una subscripciòn';
        } else {
          this._showALertMsg = true;
          this._alertMsg = 'No se ha podido completar el pago ';
        }
        console.log(_success);
      });
  }

  public newEmptyMag(): Magazine {
    return new Magazine(
      '',
      0,
      0,
      0,
      '0000-00-00',
      '',
      false,
      false,
      '',
      '',
      false,
      []
    );
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    if (
      (window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop) > this._showScrollHeight
    ) {
      this._showGoUpButton = true;
    } else if (
      this._showGoUpButton &&
      (window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop) < this._hideScrollHeight
    ) {
      this._showGoUpButton = false;
    }
  }
}
