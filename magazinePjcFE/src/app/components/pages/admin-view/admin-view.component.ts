import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/SignUpMessge.module';
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

  private configureLinksArray(): string[] {
    return [
      Routes.APPROVE_MAGAZINE,
      Routes.ADVERTISER_LIST,
      Routes.NEW_ADVERTISER,
      Routes.REPORT_PANEL,
    ];
  }
}
