import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  CompanyEarningByMag,
  EarningByAdvertiser,
  EarningsResultReport,
  EditorEarningReport,
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

  public getEditorEarningsRep(
    _date1: string,
    _date2: string,
    _editor: string
  ): Observable<EditorEarningReport[]> {
    return this._http.get<EditorEarningReport[]>(
      `${APIs.EDITOR_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=earnings&editor=${_editor}`
    );
  }

  public getEarnigsByMags(
    _date1: string,
    _date2: string
  ): Observable<CompanyEarningByMag[]> {
    return this._http.get<CompanyEarningByMag[]>(
      `${APIs.ADMIN_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=earns-mags`
    );
  }

  public getEarningsByAdvertiser(_date1: string, _date2: string) {
    return this._http.get<EarningByAdvertiser[]>(
      `${APIs.ADMIN_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=earns-advers`
    );
  }

  public getMostCommentedMags(_date1: string, _date2: string) {
    return this._http.get<MagazineCommentsReport[]>(
      `${APIs.ADMIN_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=most-commented`
    );
  }

  public getMostPopularMags(_date1: string, _date2: string) {
    return this._http.get<MagazineSubscriptionReport[]>(
      `${APIs.ADMIN_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=most-subscribed`
    );
  }

  public getTotalEarnings(
    _date1: string,
    _date2: string
  ): Observable<EarningsResultReport[]> {
    return this._http.get<EarningsResultReport[]>(
      `${APIs.ADMIN_HTML_REPORT}?date-start=${_date1}&date-end=${_date2}&type=total-earnings`
    );
  }
}
