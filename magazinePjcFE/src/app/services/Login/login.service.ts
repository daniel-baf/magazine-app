import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { APIs } from 'src/app/vars/enums/API';
import { User } from 'src/app/modules/user.module';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private httpClient: HttpClient) {}

  validateUser(_email: string, _password: string) {
    let user: User = new User(_email, _password);

    alert(`logueando a ${_email} con contraseña ${_password}`);
    this.httpClient.post<User>(`${APIs.BACKEND}Login`, user);
  }
}
