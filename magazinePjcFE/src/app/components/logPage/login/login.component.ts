import { Component, Injectable, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpMessage } from 'src/app/modules/SignUpMessge.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { LoginService } from 'src/app/services/Logs/login.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';
import { USERS_VARS } from 'src/app/vars/enums/USER_VARS';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  _loginForm: FormGroup;
  _showError: boolean = false;
  _error: string = '';
  _isBackendError: boolean = false;

  constructor(
    private _redirecter: RedirectService,
    private _loginService: LoginService,
    private _localStorageService: LocalStorageService
  ) {
    this._loginForm = this.createFormGroup();
  }

  ngOnInit(): void {}

  // redirect to signup page
  redirectSignUp(e: Event) {
    e.preventDefault();
    this._redirecter.redirect(Routes.SIGNUP);
  }

  // validate the user from BACKEND data and redirect if token is ok
  login() {
    if (this._loginForm.valid) {
      // form valid
      // reset messages
      this._showError = false;
      this._isBackendError = false;
      // call service
      this.continueLOgin(
        this._loginForm.get('_email')?.value,
        this._loginForm.get('_password')?.value
      );
    } else {
      this._showError = true;
    }
  }

  public continueLOgin(_email: string, _password: string) {
    // call service
    this._loginService.validateUser(_email, _password).subscribe(
      // success -> redirect
      (_success: SignUpMessage) => {
        if (_success.message === 'NO_ERROR') {
          this._localStorageService.setItem(_success.user, 'user');
          this._redirecter.redirect(Routes.PAGES);
        } else {
          this._showError = true;
        }
      },
      (_error: Error) => {
        // error -> show message
        this._isBackendError = true;
        if (_error.name === 'HttpErrorResponse') {
          console.log(_error);

          _error.message = 'No hay conexion al servidor';
        }
        this._localStorageService.clear();
        this._error = _error.message;
      }
    );
  }

  // create a formgroup to reactive form
  createFormGroup(): FormGroup {
    return new FormGroup({
      _email: new FormControl('', [Validators.required, Validators.email]),
      _password: new FormControl('', [Validators.required]),
    });
  }
}
