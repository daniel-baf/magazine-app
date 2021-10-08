import { Component, Input, OnInit } from '@angular/core';
import { StringArrayMessage } from 'src/app/modules/Messages/StringArrayMessage.module';
import { User } from 'src/app/modules/Users/user.module';
import { CategoriesService } from 'src/app/services/CategoriesActions.service';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { LoginService } from 'src/app/services/Logs/login.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css'],
})
export class EditProfileComponent implements OnInit {
  _user: User;
  _categories: string[];
  _message: StringArrayMessage;
  _showMessageNoCateg: boolean = false;

  constructor(
    private _loginService: LoginService,
    private _localStorageService: LocalStorageService,
    private _categoryService: CategoriesService
  ) {
    this._user = JSON.parse(`${this._localStorageService.getData('user')}`);
  }

  ngOnInit(): void {
    // get basic info

    this._loginService.getInfoUser(this._user.email).subscribe(
      (_success: User) => {
        this._user = _success;
      },
      (_error: Error) => {
        console.log(`Error: ${_error}`);
      }
    );
    // get categories
    this._categoryService.getCategoriesUser(this._user.email).subscribe(
      (_success: StringArrayMessage) => {
        this._categories = _success.array;
        if (this._categories.length === 0) {
          this._showMessageNoCateg = true;
        }
      },
      (_error: Error) => {
        console.log(`Error: ${_error.message}`);
      }
    );
  }
}
