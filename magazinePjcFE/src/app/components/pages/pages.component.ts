import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/SignUpMessge.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css'],
})
export class PagesComponent implements OnInit {
  _user: User;
  _userTypes: string[] = ['READER', 'EDITOR', 'ADMIN'];

  constructor(private _storageService: LocalStorageService) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
  }

  ngOnInit(): void {}
}
