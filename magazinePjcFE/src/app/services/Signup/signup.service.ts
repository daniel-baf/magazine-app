import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SignUpMessage } from 'src/app/modules/Users/SignUpMessge.module';
import { User } from 'src/app/modules/Users/user.module';
import { APIs } from 'src/app/vars/enums/API';
import { USERS_VARS } from 'src/app/vars/enums/USER_VARS';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  constructor(private _http: HttpClient) {}

  signUpStep1(_user: User): Observable<SignUpMessage> {
    return this._http.post<SignUpMessage>(
      `${APIs.BACKEND}/SignUpController`,
      _user
    );
  }
}
