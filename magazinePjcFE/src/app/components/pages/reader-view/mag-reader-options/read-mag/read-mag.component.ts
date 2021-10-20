import { Component, HostListener, OnInit } from '@angular/core';
import { User } from 'src/app/modules/SignUpMessge.module';
import { SubscriptionMag } from 'src/app/modules/SubscriptionMessage.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { SubscriptionService } from 'src/app/services/Magazine/Subscription.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-read-mag',
  templateUrl: './read-mag.component.html',
  styleUrls: ['./read-mag.component.css'],
})
export class ReadMagComponent implements OnInit {
  private _user: User;
  public _subs: Array<SubscriptionMag>;
  public _postListUrl: string = Routes.MAGAZINE_POST_LIST;
  // INIFINITE SCROLL
  public _actualPage: number;
  public _offset: number;
  public _limit: number = 20;
  public _finishPage: number = 50;
  public _showScrollHeight: number = 400;
  public _hideScrollHeight: number = 200;
  public _showGoUpButton: boolean;

  constructor(
    private _storageService: LocalStorageService,
    private _subService: SubscriptionService
  ) {
    // get basic and needed info
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this._subs = new Array<SubscriptionMag>();
    this._offset = 0;
    this._actualPage = 1;
  }

  ngOnInit(): void {
    this.getValidSubs();
  }

  // FOR TS WORKOUT
  private getValidSubs(): void {
    this._subService
      .getActiveSubs(this._user.email, this._limit, this._offset)
      .subscribe((_success: SubscriptionMag[]) => {
        for (const _sub of _success) {
          this._subs.push(_sub);
        }
        this._offset += +this._limit;
      });
  }

  // INFINITE SCROLL
  public onScroll() {
    if (this._actualPage < this._finishPage) {
      this.getValidSubs();
      this._actualPage++;
    } else {
      console.log('No more lines. Finish page!');
    }
  }

  public scrollTop() {
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
