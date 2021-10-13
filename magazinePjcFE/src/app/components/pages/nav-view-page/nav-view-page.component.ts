import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-nav-view-page',
  templateUrl: './nav-view-page.component.html',
  styleUrls: ['./nav-view-page.component.css'],
})
export class NavViewPageComponent implements OnInit {
  _editProfLink: string;
  _user: User;

  constructor(
    private _localStorageService: LocalStorageService,
    private _router: RedirectService
  ) {
    this._editProfLink = Routes.EDIT_PROFILE;
    this._user = JSON.parse(`${this._localStorageService.getData('user')}`);
  }

  ngOnInit(): void {}

  logout(event: Event) {
    event.preventDefault();
    this._localStorageService.clear();
    this._router.redirect(Routes.HOME);
  }
}
