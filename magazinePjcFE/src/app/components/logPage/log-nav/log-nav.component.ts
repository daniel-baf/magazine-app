import { Component, OnInit } from '@angular/core';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-log-nav',
  templateUrl: './log-nav.component.html',
  styleUrls: ['./log-nav.component.css'],
})
export class LogNavComponent implements OnInit {
  constructor(private _redirect: RedirectService) {}

  ngOnInit(): void {}

  /* redirect to login page */
  redirectLogin(e: Event) {
    e.preventDefault();
    this._redirect.redirect(Routes.LOGIN);
  }

  // redirect to signup page
  redirectSignUp(e: Event) {
    e.preventDefault();
    this._redirect.redirect(Routes.SIGNUP);
  }

  redirectHome(e: Event) {
    e.preventDefault();
    this._redirect.redirect(Routes.HOME);
  }
}
