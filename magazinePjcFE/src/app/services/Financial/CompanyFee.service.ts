import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { APIs } from 'src/app/vars/enums/API';

@Injectable({ providedIn: 'root' })
export class CompanyFeeService {
  constructor(private _http: HttpClient) {}

  public getFees(): Observable<number[]> {
    return this._http.get<number[]>(`${APIs.FEES_CONTROLLER}?action=ALL`);
  }
}
