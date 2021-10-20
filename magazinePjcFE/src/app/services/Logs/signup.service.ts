import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SignUpMessage, User } from 'src/app/modules/SignUpMessge.module';
import { APIs } from 'src/app/vars/enums/API';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  constructor(private _http: HttpClient) {}

  signUpStep1(_user: User): Observable<SignUpMessage> {
    console.log(_user.name);
    return this._http.post<SignUpMessage>(`${APIs.SIGNUP}`, _user);
  }

  updateUser(_user: User): Observable<SignUpMessage> {
    return this._http.post<SignUpMessage>(APIs.UPDATE_USER, _user);
  }
}
