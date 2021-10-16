import { Component, HostListener, OnInit } from '@angular/core';
import { Magazine } from 'src/app/modules/Magazine/Magazine.module';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-magazine-list',
  templateUrl: './magazine-list.component.html',
  styleUrls: ['./magazine-list.component.css'],
})
export class MagazineListComponent implements OnInit {
  public _magsToShow: Array<Magazine>;
  public _user: User;
  public _actualPage: number;
  public _offset: number;
  public _limit: number;
  public _finishPage = 5;
  public _showGoUpButton: boolean;
  public _showScrollHeight = 400;
  public _hideScrollHeight = 200;

  constructor(
    private _magService: MagazineService,
    private _storageService: LocalStorageService
  ) {
    this._actualPage = 1;
    this._offset = 0;
    this._limit = 10;
    this._actualPage = 1;
    this._showGoUpButton = false;
  }

  ngOnInit(): void {
    this._magsToShow = new Array<Magazine>();
    // this.add40Lines();
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this.getMagazines();
  }

  onScroll() {
    if (this._actualPage < this._finishPage) {
      this.getMagazines();
      this._actualPage++;
    } else {
      console.log('No more lines. Finish page!');
    }
  }

  private getMagazines() {
    this._magService
      .getMagazineForUser(this._limit, this._offset, this._user.email)
      .subscribe(
        (_success: Magazine[]) => {
          for (const _mag of _success) {
            this._magsToShow.push(_mag);
          }
          this._offset += this._limit;
        },
        (_error: Error) => {
          console.log('ERROR');
          console.log(_error);
        }
      );
  }

  scrollTop() {
    document.body.scrollTop = 0; // Safari
    document.documentElement.scrollTop = 0; // Other
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    if (
      (window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop) > this._showScrollHeight
    ) {
      this._showGoUpButton = true;
    } else if (
      this._showGoUpButton &&
      (window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop) < this._hideScrollHeight
    ) {
      this._showGoUpButton = false;
    }
  }
}
