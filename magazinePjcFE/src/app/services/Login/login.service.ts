import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { APIs } from 'src/app/vars/enums/API';
import { User } from 'src/app/modules/user.module';
import { RedirectService } from '../redirect.service';
import { Routes } from 'src/app/vars/enums/ROUTES';
import { USERS_VARS } from 'src/app/vars/enums/USER_VARS';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private _http: HttpClient, private redirecter: RedirectService) {}

  validateUser(_email: string, _password: string) {
    this._http
      .post<User>(`${APIs.BACKEND}UserChecker`, new User(_email, _password))
      .subscribe((success: User) => {
        switch (success._type) {
          case USERS_VARS.ADMIN:
            this.redirecter.redirect(Routes.ADMIN_PAGE);
            break;
          case USERS_VARS.EDITOR:
            this.redirecter.redirect(Routes.EDITOR_PAGE);
            break;
          case USERS_VARS.READER:
            this.redirecter.redirect(Routes.READER_PAGE);
            break;
          default:
            alert('Credenciales incorrectas');
        }
      });
  }
}
