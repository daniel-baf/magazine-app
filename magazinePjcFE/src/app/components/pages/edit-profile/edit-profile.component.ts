import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SignUpMessage, User } from 'src/app/modules/SignUpMessge.module';
import { StringArrayMessage } from 'src/app/modules/ArrayMessage.module';
import { CategoriesService } from 'src/app/services/CategoriesActions.service';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { LoginService } from 'src/app/services/Logs/login.service';
import { SignupService } from 'src/app/services/Logs/signup.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { APIs } from 'src/app/vars/enums/API';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
})
export class EditProfileComponent implements OnInit {
  public _infoForm: FormGroup;
  public _user: User;
  public _errorMessage: string;
  public _alertMessage: string;
  public _successMessage: string;
  public _categories: string[];
  public _showMessageAlert: boolean = false;
  public _showMessageNoCateg: boolean = false;
  public _showErrorMessage: boolean = false;
  public _showMessageSuccess: boolean = false;
  public _messageBackendObject: StringArrayMessage;
  public _showUrlImg: string;
  public _mapUrl: SafeResourceUrl;

  constructor(
    private _loginService: LoginService,
    private signupService: SignupService,
    private _localStorageService: LocalStorageService,
    private _categoryService: CategoriesService,
    private _routerService: RedirectService,
    private _sanitizer: DomSanitizer
  ) {
    this._user = JSON.parse(`${this._localStorageService.getData('user')}`);
    this.getCategories();
    this._infoForm = this.createForm();
    this._showUrlImg = `${APIs.FILES_GIVER_CONTROLLER}?action=GET_PROF_PIC&user=${this._user.email}&type=${this._user.type}`;
    this._mapUrl = this._sanitizer.bypassSecurityTrustResourceUrl(
      this._showUrlImg
    );
  }

  ngOnInit(): void {}

  // FORM AC TIONS

  editCategories() {
    this._routerService.redirect(Routes.SELECT_CAT);
  }

  saveProfileChanges() {
    this._showErrorMessage = false;
    this.updateUserWithFormData();
    if (this._infoForm.valid) {
      this.signupService.updateUser(this._user).subscribe(
        (_success: SignUpMessage) => {
          if (_success.message === 'NO_ERROR') {
            this.showMessage('Se han aplicado los cambios');
            this._user = _success.user;
            this._localStorageService.setItem(this._user, 'user');
          } else {
            this.showALert('No se pudo procesar la peticion');
          }
        },
        (_error: Error) => {
          this.showError(_error.message);
        }
      );
    } else {
      this.showALert('Uno/s de los atributos es invalido');
    }
  }

  // TS methods
  createForm(): FormGroup {
    return new FormGroup({
      _email: new FormControl(this._user.email, [
        Validators.required,
        Validators.email,
      ]),
      _password: new FormControl('', [Validators.required]),
      _name: new FormControl(this._user.name, [Validators.required]),
      _description: new FormControl(this._user.description),
      _accountType: new FormControl(this._user.type, [Validators.required]),
    });
  }

  updateUserWithFormData() {
    this._user.name = this._infoForm.controls['_name'].value;
    this._user.password = this._infoForm.controls['_password'].value;
    this._user.description = this._infoForm.controls['_description'].value;
  }

  // CATEGORIES
  getCategories() {
    this._loginService.getInfoUser(this._user.email).subscribe(
      (_success: User) => {
        this._user = _success;
        this._infoForm.controls['_name'].setValue(this._user.name);
        this._infoForm.controls['_description'].setValue(
          this._user.description
        );
      },
      (_error: Error) => {
        this.showError(_error.message);
      }
    );
    // get categories
    if (this._user.type === 'READER') {
      this._categoryService.getCategories(this._user.email, true).subscribe(
        (_success: StringArrayMessage) => {
          this._categories = _success.array;
          if (this._categories.length === 0) {
            this._showMessageNoCateg = true;
          }
        },
        (_error: Error) => {
          this.showError(_error.message);
        }
      );
    }
  }

  // MERSSAGES
  showALert(_message: string) {
    this._showMessageAlert = true;
    this._alertMessage = _message;
  }

  showError(_message: string) {
    this._showErrorMessage = true;
    this._errorMessage = _message;
  }

  showMessage(_message: string) {
    this._showMessageSuccess = true;
    this._successMessage = _message;
  }
}
