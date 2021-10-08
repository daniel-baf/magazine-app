import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StringArrayMessage } from '../modules/Messages/StringArrayMessage.module';
import { APIs } from '../vars/enums/API';

@Injectable({
  providedIn: 'root',
})
export class CategoriesService {
  constructor(private _httpCLientService: HttpClient) {}

  getCategoriesUser (_email: string): Observable<StringArrayMessage> {
    return this._httpCLientService.get<StringArrayMessage>(
      `${APIs.BACKEND}CategoriesSelectContoller?email=${_email}&action=BY_USER`
    );
  }
}
