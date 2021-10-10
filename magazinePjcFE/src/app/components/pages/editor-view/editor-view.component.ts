import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-editor-view',
  templateUrl: './editor-view.component.html',
  styleUrls: ['./editor-view.component.css'],
})
export class EditorViewComponent implements OnInit {
  _user: User;

  constructor(
    private _router: RedirectService,
    private _storageService: LocalStorageService
  ) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
  }

  ngOnInit(): void {}

  redirectEditProfile(e: Event) {
    e.preventDefault();
    this._router.redirect(Routes.EDIT_PROFILE);
  }
}
