import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SignUpMessage } from 'src/app/modules/Messages/SignUpMessge.module';
import { User } from 'src/app/modules/Users/user.module';
import { APIs } from 'src/app/vars/enums/API';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  constructor(private _http: HttpClient) {}

  signUpStep1(_user: User): Observable<SignUpMessage> {
    return this._http.post<SignUpMessage>(`${APIs.SIGNUP}`, _user);
  }

  updateUser(_user: User): Observable<SignUpMessage> {
    return this._http.post<SignUpMessage>(APIs.UPDATE_USER,_user);
  }
}
