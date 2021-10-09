import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SignUpMessage } from 'src/app/modules/Messages/SignUpMessge.module';
import { StringArrayMessage } from 'src/app/modules/Messages/StringArrayMessage.module';
import { User } from 'src/app/modules/Users/user.module';
import { CategoriesService } from 'src/app/services/CategoriesActions.service';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { LoginService } from 'src/app/services/Logs/login.service';
import { SignupService } from 'src/app/services/Logs/signup.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
})
export class EditProfileComponent implements OnInit {
  _infoForm: FormGroup;
  _user: User;
  _errorMessage: string;
  _alertMessage: string;
  _categories: string[];
  _messageBackendObject: StringArrayMessage;
  _showMessageAlert: boolean = false;
  _showMessageNoCateg: boolean = false;
  _showErrorMessage: boolean = false;

  constructor(
    private _loginService: LoginService,
    private signupService: SignupService,
    private _localStorageService: LocalStorageService,
    private _categoryService: CategoriesService,
    private _routerService: RedirectService
  ) {
    this._user = JSON.parse(`${this._localStorageService.getData('user')}`);
    this._infoForm = this.createForm();
  }

  ngOnInit(): void {
    // get basic info

    this._loginService.getInfoUser(this._user.email).subscribe(
      (_success: User) => {
        this._user = _success;
        this._infoForm.controls['_name'].setValue(this._user.name);
      },
      (_error: Error) => {
        this._showErrorMessage = true;
        this._errorMessage = _error.message;
      }
    );
    // get categories
    this._categoryService.getCategories(this._user.email, true).subscribe(
      (_success: StringArrayMessage) => {
        this._categories = _success.array;
        if (this._categories.length === 0 && this._user.type === 'reader') {
          this._showMessageNoCateg = true;
        }
      },
      (_error: Error) => {
        console.log(`Error: ${_error.message}`);
      }
    );
  }

  createForm(): FormGroup {
    return new FormGroup({
      _email: new FormControl(this._user.email, [
        Validators.required,
        Validators.email,
      ]),
      _password: new FormControl(this._user.password, [Validators.required]),
      _name: new FormControl(this._user.name, [Validators.required]),
      _description: new FormControl(this._user.description),
      _accountType: new FormControl(this._user.type, [Validators.required]),
    });
  }

  editCategories() {
    this._routerService.redirect(Routes.SIGNUP_SELECT_CAT);
  }

  saveProfileChanges() {
    this._showErrorMessage = false;
    this.signupService.test();
    // if (this._infoForm.valid) {
    //   // call service
    //   this.signupService.updateUser(this._user).subscribe(
    //     (_success: SignUpMessage) => {
    //       console.log(_success);
    //     },
    //     (_error: Error) => {
    //       this._errorMessage = _error.message;
    //       this._showErrorMessage = true;
    //     }
    //   );
    // } else {
    //   this.showALert('Uno/s de los atributos es invalido');
    // }
  }

  showALert(_message: string) {
    this._showMessageAlert = true;
    this._alertMessage = _message;
  }
}
