import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/modules/SignUpMessge.module';
import { JasperService } from 'src/app/services/fileGiver.service';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { APIs } from 'src/app/vars/enums/API';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-request-report',
  templateUrl: './request-report.component.html',
  styleUrls: ['./request-report.component.css'],
})
export class RequestReportComponent implements OnInit {
  public _dateStart: string = '';
  public _dateEnd: string = '';
  public _action: string = '';
  public _errorMessage: string = '';
  public _showError: boolean = false;
  constructor(
    private _localStorage: LocalStorageService,
    private _router: Router
  ) {}

  ngOnInit(): void {}

  public requestReport() {
    if (this.isValid()) {
      this._router.navigate([
        Routes.READ_REPORT,
        this._action,
        this._dateEnd,
        this._dateStart,
      ]);
    }
  }

  public isValid(): boolean {
    if (this._action == '') {
      this._showError = true;
      this._errorMessage = 'no has seleccionado una categoria valida';
      return false;
    }
    return true;
  }
}
