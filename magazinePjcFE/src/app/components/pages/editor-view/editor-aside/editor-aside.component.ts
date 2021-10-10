import { Component, OnInit } from '@angular/core';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-editor-aside',
  templateUrl: './editor-aside.component.html',
  styleUrls: ['./editor-aside.component.css'],
})
export class EditorAsideComponent implements OnInit {
  _newMagLink: string;

  constructor() {
    this._newMagLink = Routes.NEW_MAGAZINE;
  }

  ngOnInit(): void {}
}
