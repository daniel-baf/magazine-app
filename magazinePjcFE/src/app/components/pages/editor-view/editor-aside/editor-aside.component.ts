import { Component, OnInit } from '@angular/core';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-editor-aside',
  templateUrl: './editor-aside.component.html',
  styleUrls: ['./editor-aside.component.css'],
})
export class EditorAsideComponent implements OnInit {
  public _newMagLink: string;
  public _uploadPostUrl: string;
  public _reportsPanel: string;

  constructor() {
    this._newMagLink = Routes.NEW_MAGAZINE;
    this._uploadPostUrl = Routes.UPLOAD_POST;
    this._reportsPanel = Routes.REPORT_PANEL;
  }

  ngOnInit(): void {}
}
