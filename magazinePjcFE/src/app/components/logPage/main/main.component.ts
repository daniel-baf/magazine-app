import { Component, OnInit } from '@angular/core';
import { RedirectService } from 'src/app/services/redirect.service';
import { APIs } from 'src/app/vars/enums/API';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {
  constructor(private _router: RedirectService) {}

  ngOnInit(): void {}

  redirectSignUp() {
    this._router.redirect(Routes.SIGNUP);
  }
}
