import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  Magazine,
  MagazineMessage,
} from 'src/app/modules/MagazineMessage.module';
import { User } from 'src/app/modules/SignUpMessge.module';
import { StringArrayMessage } from 'src/app/modules/ArrayMessage.module';
import { CategoriesService } from 'src/app/services/CategoriesActions.service';
import { CompanyFeeService } from 'src/app/services/Financial/CompanyFee.service';
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
  _typingCategory: string;
  _categories: string[];
  _tagsMagazine: string[];
  _companyFee: number;
  _showErrorMessage: boolean = false;
  _showResultMessage: boolean = false;
  _showAlertMessage: boolean = false;

  constructor(
    private _loginService: LoginService,
    private _storageService: LocalStorageService,
    private _magazineService: MagazineService,
    private _categoriesService: CategoriesService,
    private _feesService: CompanyFeeService
  ) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this.updateUser();
    this.getCategories();
    this.getFees();
    this._tagsMagazine = [];
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
    if (this._magazineForm.valid && this._tagsMagazine.length > 0) {
      // create magazine
      this.createMagazine();
      this.createMagazine;
      this._magazineService
        .queueOrPublishMagazine(new MagazineMessage('QUEUE', this._magazine))
        .subscribe(
          (_success: MagazineMessage) => {
            if (_success.message === 'NO_ERROR') {
              this.showResultMessage(
                'Se ha puesto en lista de espera tu revista'
              );
            } else {
              this.showAlertMessage(
                'No se ha podido publicar tu revista, es posible que ya este registrada'
              );
            }
          },
          (_error: Error) => {
            this.showErrorMessage(_error.message);
          }
        );
    } else {
      this.showAlertMessage('Debes llenar todos los requisitos');
    }
  }

  // FOR TS PURPOSE
  public generateFormGroup(): FormGroup {
    return new FormGroup({
      _name: new FormControl('', [Validators.required]),
      _price: new FormControl('', [Validators.required, Validators.min(0)]),
      _companyFee: new FormControl(this._companyFee, [
        Validators.required,
        Validators.min(0),
      ]),
      _costPerDay: new FormControl(0),
      _date: new FormControl('', [Validators.required]),
      _description: new FormControl('', [
        Validators.required,
        Validators.maxLength(3000),
      ]),
      _allowLikes: new FormControl(true),
      _allowComment: new FormControl(true),
      _category: new FormControl('NO_VALID', [Validators.required]),
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
      false,
      this._tagsMagazine
    );
  }

  public addCategory() {
    this._showAlertMessage = false;
    if (this._typingCategory == undefined || this._typingCategory === null) {
      this.showAlertMessage('NO puedes agregar vacio');
    } else {
      this._tagsMagazine.push(this._typingCategory);
      this._typingCategory = '';
    }
  }

  public removeCategory() {}

  public getFees() {
    this._feesService.getFees().subscribe((_success: number[]) => {
      this._companyFee = _success[0];
      this._magazineForm.controls['_companyFee'].setValue(this._companyFee);
    });
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
