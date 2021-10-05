import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/Login/login.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  _loginForm: FormGroup;
  _showError: boolean = false;

  constructor(private _redirecter: RedirectService) {
    this._loginForm = this.createFormGroup();
  }

  ngOnInit(): void {}

  redirectSignUp(e: Event) {
    e.preventDefault();
    this._redirecter.redirect(Routes.SIGNUP);
  }

  login() {
    if (this._loginForm.valid) {
      this._showError = false;
      alert('redirect');
    } else {
      this._showError = true;
    }
  }

  createFormGroup(): FormGroup {
    return new FormGroup({
      _email: new FormControl('', [Validators.required, Validators.email]),
      _password: new FormControl('', [Validators.required]),
    });
  }
}
