import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SignUpMessage } from 'src/app/modules/Users/SignUpMessge.module';
import { User } from 'src/app/modules/Users/user.module';
import { LoginService } from 'src/app/services/Login/login.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { SignupService } from 'src/app/services/Signup/signup.service';
import { Routes } from 'src/app/vars/enums/ROUTES';
import { USERS_VARS } from 'src/app/vars/enums/USER_VARS';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  showMessageError = false;
  _signUpForm: FormGroup;
  _noDescriptionGiven = false;
  _backendMessage: string =
    'Ha ocurrido un error, verifica que tus datos sean correctos';
  _showBackendMessage = false;

  constructor(
    private _router: RedirectService,
    private _signupService: SignupService,
    private _loginService: LoginService
  ) {
    this._signUpForm = this.createFormGroup();
  }

  ngOnInit(): void {}

  // create the user, and send data to BACKEND
  procedSignUp() {
    // hide all posible alert messages
    this._noDescriptionGiven = false;
    this.showMessageError = false;
    this._showBackendMessage = false;
    // svalidate form
    if (this._signUpForm.valid) {
      // if choose editor, description is requiered
      if (this._signUpForm.get('_isEditor')?.value && this.noDescription()) {
        this._noDescriptionGiven = true;
        this._showBackendMessage = false;
      } else {
        // ALL DATA OK
        if (this.createUser()) {
          // Redirect to choose categories
        }
      }
    } else {
      // TODO show message error
      this.showMessageError = true;
    }
    // AJAX to call the intrests
  }

  private noDescription(): boolean {
    return (
      this._signUpForm.get('_description')?.value === null ||
      this._signUpForm.get('_description')?.value.trim() === ''
    );
  }

  private createUser(): boolean {
    // create user
    let _success = false;

    let type = this._signUpForm.get('_isEditor')?.value
      ? USERS_VARS.EDITOR
      : USERS_VARS.READER;

    this._signupService
      .signUpStep1(
        new User( // OBJECT json
          this._signUpForm.get('_email')?.value,
          this._signUpForm.get('_password')?.value,
          type,
          this._signUpForm.get('_description')?.value,
          this._signUpForm.get('_name')?.value
        )
      )
      .subscribe(
        // return a message and User object
        (success: SignUpMessage) => {
          if (success.message === 'ERROR_INSERT') {
            this._showBackendMessage = true;
            this._backendMessage =
              'Ha ocurrido un error al procesar la solicitud';
          } else if (success.message === 'EMAIL_IN_USE') {
            this._showBackendMessage = true;
            this._backendMessage = 'Email en uso';
          } else if (success.message === 'NO_ERROR') {
            // execute login
            _success = true;
          }
        },
        (_error: Error) => {
          this._showBackendMessage = true;
          this._backendMessage = _error.message;
        }
      );

    return _success;
  }

  // CREATE A FORM
  createFormGroup(): FormGroup {
    return new FormGroup({
      _name: new FormControl('', [Validators.required]),
      _email: new FormControl('', [Validators.required]),
      _password: new FormControl('', [Validators.required]),
      _isEditor: new FormControl(false),
      _description: new FormControl(''),
    });
  }
}
