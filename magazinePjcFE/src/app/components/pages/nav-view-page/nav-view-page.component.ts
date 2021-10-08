import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
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
    private _localStorageService: LocalStorageService
  ) {
    this._editProfLink = Routes.EDIT_PROFILE;
    this._user = JSON.parse(`${this._localStorageService.getData('user')}`);
  }

  ngOnInit(): void {}
}
