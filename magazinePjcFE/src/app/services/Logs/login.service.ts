import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { APIs } from 'src/app/vars/enums/API';
import { Observable } from 'rxjs';
import { SignUpMessage, User } from 'src/app/modules/SignUpMessge.module';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  prueba: string = ' pepe ';
  constructor(private _http: HttpClient) {}

  validateUser(_email: string, _password: string): Observable<SignUpMessage> {
    return this._http.post<SignUpMessage>(
      `${APIs.CHECK_USER}`,
      new User(_email, _password)
    );
  }

  getInfoUser(_email: string): Observable<User> {
    return this._http.get<User>(
      `${APIs.CHECK_USER}?email=${_email}&action=BASIC_INFO`
    );
  }
}
