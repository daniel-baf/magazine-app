import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { RedirectService } from 'src/app/services/redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css'],
})
export class AdminViewComponent implements OnInit {
  _user: User;
  _links: string[];

  constructor(
    private _router: RedirectService,
    private _storageService: LocalStorageService
  ) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this._links = this.configureLinksArray();
  }

  ngOnInit(): void {}

  redirectSubAdminPage(_event: Event, _towards: String) {
    switch (_towards) {
      case 'APPROVE_MAG':
        break;
    }
  }

  private configureLinksArray(): string[] {
    return [Routes.APPROVE_MAGAZINE];
  }
}
