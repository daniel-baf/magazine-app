import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StringArrayMessage } from '../modules/ArrayMessage.module';
import { APIs } from '../vars/enums/API';

@Injectable({
  providedIn: 'root',
})
export class CategoriesService {
  constructor(private _httpCLientService: HttpClient) {}

  getCategories(
    _email: string,
    _byUser: boolean = false
  ): Observable<StringArrayMessage> {
    let action = _byUser ? 'BY_USER' : 'ALL';
    return this._httpCLientService.get<StringArrayMessage>(
      `${APIs.CATEGORY_CRLLER}?email=${_email}&action=${action}`
    );
  }

  saveUserCategories(
    _array: string[],
    _email: string
  ): Observable<StringArrayMessage> {
    return this._httpCLientService.post<StringArrayMessage>(
      APIs.CATEGORY_CRLLER,
      new StringArrayMessage(_email, _array)
    );
  }
}
