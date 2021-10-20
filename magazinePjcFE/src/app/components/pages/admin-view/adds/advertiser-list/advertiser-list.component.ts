import { Component, HostListener, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { APIs } from 'src/app/vars/enums/API';

@Component({
  selector: 'app-advertiser-list',
  templateUrl: './advertiser-list.component.html',
  styleUrls: ['./advertiser-list.component.css'],
})
export class AdvertiserListComponent implements OnInit {
  // page
  _mapUrl: SafeResourceUrl;
  _showTmp: string = `${APIs.FILES_GIVER_CONTROLLER}?action=SHOW_PDF&id=1`;

  // INIFINITE SCROLL
  public _actualPage: number;
  public _offset: number;
  public _limit: number = 20;
  public _finishPage: number = 5;
  public _showScrollHeight: number = 400;
  public _hideScrollHeight: number = 200;
  public _showGoUpButton: boolean;

  constructor(private _sanitizer: DomSanitizer) {
    this._mapUrl = this._sanitizer.bypassSecurityTrustResourceUrl(
      this._showTmp
    );
  }

  ngOnInit(): void {}

  // FOR TS workout
  private getAdvertisers() {}

  // INFINITE SCROLL
  public onScroll() {
    if (this._actualPage < this._finishPage) {
      this.getAdvertisers();
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
