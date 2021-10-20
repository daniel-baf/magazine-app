import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { LoginService } from 'src/app/services/Logs/login.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { SignupService } from 'src/app/services/Logs/signup.service';
import { USERS_VARS } from 'src/app/vars/enums/USER_VARS';
import { LoginComponent } from '../login/login.component';
import { SignUpMessage, User } from 'src/app/modules/SignUpMessge.module';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  showMessageError = false;
  _noDescriptionGiven = false;
  _showBackendMessage = false;
  _signUpForm: FormGroup;
  _user: User;
  _backendMessage: string =
    'Ha ocurrido un error, verifica que tus datos sean correctos';

  constructor(
    private _signupService: SignupService,
    private _router: RedirectService,
    private _loginService: LoginService,
    private _localStorageService: LocalStorageService
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
        this.showMessageError = false;
        this._showBackendMessage = false;
      } else {
        // ALL DATA OK
        this.createUser();
      }
    } else {
      this.showMessageError = true;
      console.log(this._signUpForm.controls['_name'].valid);
      console.log(this._signUpForm.controls['_email'].valid);
      console.log(this._signUpForm.controls['_isEditor'].valid);
      console.log(this._signUpForm.controls['_password'].valid);
      console.log(this._signUpForm.controls['_description'].valid);
    }
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
          console.log(success);

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
            this._user = success.user;
            this.autologin();
          }
        },
        (_error: Error) => {
          this._showBackendMessage = true;
          this._backendMessage = _error.message;
        }
      );

    return _success;
  }

  autologin() {
    alert('done! now redirect');
    // Redirect to choose categories
    new LoginComponent(
      this._router,
      this._loginService,
      this._localStorageService
    ).continueLOgin(this._user.email, this._user.password);
  }

  // CREATE A FORM
  createFormGroup(): FormGroup {
    return new FormGroup({
      _name: new FormControl('', [Validators.required]),
      _email: new FormControl('', [Validators.required, Validators.email]),
      _password: new FormControl('', [Validators.required]),
      _isEditor: new FormControl(false),
      _description: new FormControl(''),
    });
  }
}
