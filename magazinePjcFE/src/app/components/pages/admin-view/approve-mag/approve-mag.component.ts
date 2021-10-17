import { Component, OnInit } from '@angular/core';
import {
  Magazine,
  MagazineMessage,
} from 'src/app/modules/MagazineMessage.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-approve-mag',
  templateUrl: './approve-mag.component.html',
  styleUrls: ['./approve-mag.component.css'],
})
export class ApproveMagComponent implements OnInit {
  _toShow: number;
  _magazines: Magazine[];
  _toAdd: number = 5;
  _activeMag: Magazine;
  _showMessage: boolean = false;
  _showSuccess: boolean = false;

  constructor(
    private _magService: MagazineService,
    private _localStorageService: LocalStorageService
  ) {
    this.configCuanitytToShow();
    this._activeMag = this.newEmptyMag();
  }

  ngOnInit(): void {
    this.getMagazines();
  }

  private getMagazines() {
    this._magService.getNoPublishedMags(this._toShow).subscribe(
      (_success: Magazine[]) => {
        this._magazines = _success;
      },
      (_error: Error) => {
        console.log('ERROR');
        console.log(_error);
      }
    );
  }

  private newEmptyMag(): Magazine {
    return new Magazine('', 0, 0, 0, '', '', false, false, '', '', false, []);
  }

  public showMoreMagazines() {
    this._toShow += +this._toAdd;
    this._localStorageService.setItem(this._toShow, 'mag-to-show-approve');
    this.configCuanitytToShow();
    this.getMagazines();
  }

  public resetItemsToShow() {
    this._localStorageService.setItem(10, 'mag-to-show-approve');
    this.configCuanitytToShow();
    this.getMagazines();
  }

  private configCuanitytToShow() {
    if (this._localStorageService.getData('mag-to-show-approve') === null) {
      this._localStorageService.setItem(10, 'mag-to-show-approve');
      this._toShow = 10;
    } else {
      this._toShow = JSON.parse(
        `${this._localStorageService.getData('mag-to-show-approve')}`
      );
    }
  }

  // FOR MAGAZINE
  public setMagTmp(_mag: Magazine) {
    this._activeMag = _mag;
  }

  public removeActualMag() {
    this._activeMag = this.newEmptyMag();
  }

  public denyPublishment() {}

  public approveMag() {
    if (this._activeMag.costPerDay <= 0) {
      this._showMessage = true;
    } else {
      this._magService
        .updateMagazine(new MagazineMessage('UPDATE', this._activeMag))
        .subscribe((_success: MagazineMessage) => {
          if (_success.message === 'NO_ERROR') {
            this._showSuccess = true;
            this.getMagazines();
          } else {
            this._showMessage = true;
          }
        });
    }
  }
}
