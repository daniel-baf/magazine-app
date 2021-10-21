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

  signUp(_user: User, _file: File | null): Observable<SignUpMessage> {
    let _formData = new FormData();
    _formData.append('user', JSON.stringify(_user));
    if (_file != null) {
      _formData.append('profile-pic', _file, _file?.name);
    }
    return this._http.post<SignUpMessage>(`${APIs.SIGNUP}`, _formData);
  }

  updateUser(_user: User): Observable<SignUpMessage> {
    return this._http.post<SignUpMessage>(APIs.UPDATE_USER, _user);
  }
}
