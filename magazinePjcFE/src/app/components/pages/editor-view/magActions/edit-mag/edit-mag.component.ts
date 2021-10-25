import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {
  Magazine,
  MagazineMessage,
} from 'src/app/modules/MagazineMessage.module';
import { User } from 'src/app/modules/SignUpMessge.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-edit-mag',
  templateUrl: './edit-mag.component.html',
  styleUrls: ['./edit-mag.component.css'],
})
export class EditMagComponent implements OnInit {
  _user: User;
  _errorMessage: string;
  _statusMagazine: string;
  _resultMessage: string;
  _typingCategory: string;
  _showErrorMessage: boolean = false;
  _showResultMessage: boolean = false;
  // data from DB
  _magazineDB: Magazine;

  constructor(
    private _storageService: LocalStorageService,
    private _magazineService: MagazineService,
    private _route: ActivatedRoute
  ) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this.getMagInfo();
  }

  ngOnInit(): void {}

  // FOR TS PURPOSE

  public addTag() {
    if (
      this._typingCategory == undefined ||
      this._typingCategory.trim() == ''
    ) {
      this.showErrorMessage('NO puedes agregar vacio');
    } else {
      let index = this._magazineDB.tags.indexOf(this._typingCategory);
      if (index === -1) {
        this._magazineDB.tags.push(this._typingCategory);
        this._typingCategory == '';
      }
      // todo add tags
    }
  }

  public removeCategory(_tag: string) {
    let index = this._magazineDB.tags.indexOf(_tag);
    if (index != -1 && this._magazineDB.tags.length > 1) {
      this._magazineDB.tags.splice(index, 1);
    } else {
      this.showErrorMessage('Las revistas deben tener al menos 1 etiqueta');
    }
  }

  // REQUESTS
  // ALERTS
  public showErrorMessage(_message: string) {
    this._showErrorMessage = true;
    this._errorMessage = _message;
  }

  public showResultMessage(_message: string) {
    this._showResultMessage = true;
    this._resultMessage = _message;
  }

  private getMagInfo() {
    this._magazineDB = new Magazine(
      '',
      0,
      0,
      0,
      '',
      '',
      false,
      false,
      '',
      '',
      false,
      [],
      false
    );
    this._magazineService
      .getMagazine(`${this._route.snapshot.paramMap.get('id')}`)
      .subscribe((_success: Magazine[]) => {
        this._magazineDB = _success[0];
      });
  }

  public updateMag() {
    // validation
    if (this._magazineDB.mensuality < 0 || this._magazineDB.tags.length <= 0) {
      this.showErrorMessage('debes llenar los campos obligatorios');
    } else {
      // generate MagazineMessage
      this._magazineService
        .updateMagazineInfo(
          new MagazineMessage('UPDATE_MAG_VALUES', this._magazineDB)
        )
        .subscribe((_success: MagazineMessage) => {
          if (_success.message == 'NO_ERROR') {
            this.showResultMessage('Se ha modificado la revista');
          } else {
            this.showErrorMessage(
              'No se ha podido modificar: ' + _success.message
            );
          }
        });
    }
  }
}
