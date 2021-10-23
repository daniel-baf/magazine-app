import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/modules/SignUpMessge.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { APIs } from 'src/app/vars/enums/API';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css'],
})
export class ReportComponent implements OnInit {
  private _user: User;
  // return parms from ul
  public _repUrlSafe: SafeResourceUrl;
  public _customMessage: string;

  constructor(
    private _localStorage: LocalStorageService,
    private _route: ActivatedRoute,
    private _sanitizer: DomSanitizer
  ) {
    this._user = JSON.parse(`${this._localStorage.getData('user')}`);
    this.configureLink();
  }

  ngOnInit(): void {}

  private configureLink() {
    let _repTye = this._route.snapshot.paramMap.get('rep-type');
    let dateStart = this._route.snapshot.paramMap.get('dateS');
    let dateEnd = this._route.snapshot.paramMap.get('dateE');
    if (this._user.type === 'EDITOR') {
      this._repUrlSafe = this._sanitizer.bypassSecurityTrustResourceUrl(
        `${APIs.JASPER_REPORT_CONTROLLER}?type=EDITOR&action=${_repTye}&date-end=${dateEnd}&date-start=${dateStart}&owner=${this._user.email}`
      );
    } else if (this._user.type === 'ADMIN') {
      this._repUrlSafe = this._sanitizer.bypassSecurityTrustResourceUrl(
        `${APIs.JASPER_REPORT_CONTROLLER}?type=ADMIN&action=${_repTye}&date-end=${dateEnd}&date-start=${dateStart}`
      );
    }
    this.configureCustomMessage(dateStart, dateEnd);
  }

  private configureCustomMessage(
    _dateStart: String | null,
    _dateEnd: string | null
  ) {
    if (_dateEnd != null && _dateStart != null) {
      if (_dateEnd != '' && _dateStart != '') {
        this._customMessage = `Se ha generado un reporte, el reporte cumple desde la fecha ${_dateStart} hasta la fecha ${_dateEnd}`;
      } else {
        this._customMessage = `El reporte generado muestra resultados desde el primer dia de publicación hasta el dia de hoy`;
      }
    }
  }
}
