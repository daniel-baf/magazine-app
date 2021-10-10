import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Magazine } from 'src/app/modules/Magazine/Magazine.module';
import { MagazineMessage } from 'src/app/modules/Messages/MagazineMessage.module';
import { StringArrayMessage } from 'src/app/modules/Messages/StringArrayMessage.module';
import { User } from 'src/app/modules/Users/user.module';
import { CategoriesService } from 'src/app/services/CategoriesActions.service';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { LoginService } from 'src/app/services/Logs/login.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-new-magazine',
  templateUrl: './new-magazine.component.html',
  styleUrls: ['./new-magazine.component.css'],
})
export class NewMagazineComponent implements OnInit {
  _user: User;
  _magazineForm: FormGroup;
  _magazine: Magazine;
  _errorMessage: string;
  _statusMagazine: string;
  _resultMessage: string;
  _alertMessage: string;
  _categories: string[];
  _showErrorMessage: boolean = false;
  _showResultMessage: boolean = false;
  _showAlertMessage: boolean = false;

  constructor(
    private _loginService: LoginService,
    private _storageService: LocalStorageService,
    private _magazineService: MagazineService,
    private _categoriesService: CategoriesService
  ) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this.updateUser();
    this.getCategories();
    this._magazineForm = this.generateFormGroup();
  }

  ngOnInit(): void {}

  private updateUser() {
    this._loginService.getInfoUser(this._user.email).subscribe(
      (_success: User) => {
        this._user = _success;
      },
      (_error) => {
        this.showErrorMessage(_error.message);
      }
    );
  }

  // METHOD
  public queueMagazine() {
    // create user
    if (this._magazineForm.valid) {
      // create magazine
      this.createMagazine();
      console.log('ENVIANDO');

      console.log(this._magazine);

      this.createMagazine;
      this._magazineService
        .queueOrPublishMagazine(new MagazineMessage('QUEUE', this._magazine))
        .subscribe((_success: MagazineMessage) => {
          console.log(_success);
        });
    } else {
      this.showAlertMessage('Debes llenar todos los requisitos');
    }
  }

  // FOR TS PURPOSE
  public generateFormGroup(): FormGroup {
    return new FormGroup({
      _name: new FormControl('', [Validators.required]),
      _price: new FormControl('', [Validators.required, Validators.min(0)]),
      _companyFee: new FormControl('', [
        Validators.required,
        Validators.min(0),
      ]),
      _costPerDay: new FormControl(''),
      _date: new FormControl('', [Validators.required]),
      _description: new FormControl('', [
        Validators.required,
        Validators.maxLength(3000),
      ]),
      _allowLikes: new FormControl(true),
      _allowComment: new FormControl(true),
      _category: new FormControl(this._categories, [Validators.required]),
      _editor: new FormControl(this._user.email, [Validators.required]),
    });
  }

  public createMagazine() {
    this._magazine = new Magazine(
      this._magazineForm.controls['_name'].value,
      this._magazineForm.controls['_price'].value,
      this._magazineForm.controls['_companyFee'].value,
      this._magazineForm.controls['_costPerDay'].value,
      this._magazineForm.controls['_date'].value,
      this._magazineForm.controls['_description'].value,
      this._magazineForm.controls['_allowComment'].value,
      this._magazineForm.controls['_allowLikes'].value,
      this._magazineForm.controls['_category'].value,
      this._magazineForm.controls['_editor'].value,
      false
    );
  }

  // REQUESTS
  public getCategories() {
    this._categoriesService.getCategories('', false).subscribe(
      (_success: StringArrayMessage) => {
        if (_success.message === 'FOUND') {
          this._categories = _success.array;
        } else {
          this.showAlertMessage('no se encuentran categorias');
        }
      },
      (_error: Error) => {
        this.showAlertMessage(_error.message);
      }
    );
  }

  // ALERTS
  public showErrorMessage(_message: string) {
    this._showErrorMessage = true;
    this._errorMessage = _message;
  }

  public showAlertMessage(_message: string) {
    this._showAlertMessage = true;
    this._alertMessage = _message;
  }

  public showResultMessage(_message: string) {
    this._showResultMessage = true;
    this._resultMessage = _message;
  }
}
