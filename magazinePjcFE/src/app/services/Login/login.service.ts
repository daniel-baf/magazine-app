import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { APIs } from 'src/app/vars/enums/API';
import { User } from 'src/app/modules/Users/user.module';
import { RedirectService } from '../redirect.service';
import { USERS_VARS } from 'src/app/vars/enums/USER_VARS';
import { Observable } from 'rxjs';
import { SignUpMessage } from 'src/app/modules/Users/SignUpMessge.module';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private _http: HttpClient, private redirecter: RedirectService) {}

  validateUser(_email: string, _password: string): Observable<SignUpMessage> {
    return this._http.post<SignUpMessage>(
      `${APIs.BACKEND}UserChecker`,
      new User(_email, _password)
    );
  }
}
