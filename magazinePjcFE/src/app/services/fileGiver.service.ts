import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { APIs } from '../vars/enums/API';

@Injectable({
  providedIn: 'root',
})
export class JasperService {
  constructor(private _http: HttpClient){}

  public getReportEditor(
    _editorRep: string,
    _startDate: string,
    _endDate: string,
    _owner: string
  ) {
    let _formData = new FormData();
    _formData.append('type', 'EDITOR');
    _formData.append('date-start', _startDate);
    _formData.append('date-end', _endDate);
    _formData.append('action', _editorRep);
    _formData.append('action', _owner);

    return this._http.post(`${APIs.JASPER_REPORT_CONTROLLER}`, _formData);
  }
}
