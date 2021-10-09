import { Component, OnInit } from '@angular/core';
import { StringArrayMessage } from 'src/app/modules/Messages/StringArrayMessage.module';
import { User } from 'src/app/modules/Users/user.module';
import { CategoriesService } from 'src/app/services/CategoriesActions.service';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';

@Component({
  selector: 'app-select-categories',
  templateUrl: './select-categories.component.html',
  styleUrls: ['./select-categories.component.css'],
})
export class SelectCategoriesComponent implements OnInit {
  _categories: string[];
  _categoriesUser: string[];
  _user: User;
  _warningMessage: string;
  _errorMessage: string;
  _successMessage: string;
  _showWarning: boolean = false;
  _showError: boolean = false;
  _showSuccessMessage: boolean = false;

  constructor(
    private _categoriesService: CategoriesService,
    private _storageService: LocalStorageService
  ) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
  }

  ngOnInit(): void {
    // get categories
    this._categoriesService
      .getCategories('', false)
      .subscribe((_success: StringArrayMessage) => {
        if (_success.message === 'FOUND') {
          this._categories = _success.array;
        } else {
          this.showAlert('no se encuentran categorias');
        }
      });
    // get user categories
    this._categoriesService.getCategories(this._user.email, true).subscribe(
      (_success: StringArrayMessage) => {
        this._categoriesUser = _success.array;
      },
      (_error: Error) => {
        this._showError = true;
        this._errorMessage = _error.message;
      }
    );
  }

  addToList(_category: string) {
    if (this._categoriesUser.indexOf(_category) < 0) {
      this._categoriesUser.push(_category);
    } else {
      this.showAlert('Ya está en lista');
    }
  }

  removeFromList(_category: string) {
    this._categoriesUser.splice(this._categoriesUser.indexOf(_category), 1);
  }

  saveCategories() {
    if (this._categoriesUser.length > 0) {
      this._showWarning = false;
      this._showSuccessMessage = false;
      this._showError = false;

      this._categoriesService
        .saveUserCategories(this._categoriesUser, this._user.email)
        .subscribe((_success: StringArrayMessage) => {
          if (_success.message === 'SUCCESS') {
            this._successMessage = 'Se han aplicado los cambios';
            this._showSuccessMessage = true;
          } else {
            this.showAlert('Hubo un error al intentar aplicar tus cambios');
          }
        });
    } else {
      this.showAlert(
        'Recomendamos elegir al menos una categoria para una mejor experiencia'
      );
    }
  }

  private showAlert(_alert: string) {
    this._showWarning = true;
    this._warningMessage = _alert;
  }
}
