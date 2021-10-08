import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/Users/user.module';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-editor-view',
  templateUrl: './editor-view.component.html',
  styleUrls: ['./editor-view.component.css'],
})
export class EditorViewComponent implements OnInit {
  _editProfLink: string;
  _user: User;
  _email: string;

  constructor(private _router: RedirectService) {
    this._editProfLink = Routes.EDIT_PROFILE;
  }

  ngOnInit(): void {}

  redirectEditProfile(e: Event) {
    e.preventDefault();
    this._router.redirect(Routes.EDIT_PROFILE);
  }
}
