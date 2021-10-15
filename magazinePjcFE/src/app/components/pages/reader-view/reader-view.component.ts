import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-reader-view',
  templateUrl: './reader-view.component.html',
  styleUrls: ['./reader-view.component.css'],
})
export class ReaderViewComponent implements OnInit {
  _user: User;
  _magListUrl: string = Routes.MAGAZINE_LIST;
  _readerViewUrl: string = Routes.PAGES;

  constructor(private _storageService: LocalStorageService) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
  }

  ngOnInit(): void {}
}
