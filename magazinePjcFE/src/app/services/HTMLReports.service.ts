import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  MagazineCommentsReport,
  MagazineLikesReport,
  MagazineSubscriptionReport,
} from '../modules/HTMLJasperReprots.module';
import { APIs } from '../vars/enums/API';

@Injectable({ providedIn: 'root' })
export class HTMLReports {
  constructor(private _http: HttpClient) {}

  public getJSONMagComments(
    _date1: string,
    _date2: string,
    _type: string,
    _editor: string
  ): Observable<MagazineCommentsReport[]> {
    return this._http.get<MagazineCommentsReport[]>(
      `${APIs.EDITOR_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=comments-mag&editor=${_editor}`
    );
  }

  public getMagSubscriptions(
    _date1: string,
    _date2: string,
    _editor: string
  ): Observable<MagazineSubscriptionReport[]> {
    return this._http.get<MagazineSubscriptionReport[]>(
      `${APIs.EDITOR_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=subs-mag&editor=${_editor}`
    );
  }

  public getMagLikes(
    _date1: string,
    _date2: string,
    _editor: string
  ): Observable<MagazineLikesReport[]> {
    return this._http.get<MagazineLikesReport[]>(
      `${APIs.EDITOR_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=most-liked&editor=${_editor}`
    );
  }
}
