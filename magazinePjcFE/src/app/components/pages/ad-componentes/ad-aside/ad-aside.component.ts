import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Ad } from 'src/app/modules/AdsMessages.module';
import { User } from 'src/app/modules/SignUpMessge.module';
import { AddsService } from 'src/app/services/Financial/ads.service';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { APIs } from 'src/app/vars/enums/API';

@Component({
  selector: 'app-ad-aside',
  templateUrl: './ad-aside.component.html',
  styleUrls: ['./ad-aside.component.css'],
})
export class AdAsideComponent implements OnInit {
  public _textAd: Ad;
  public _imgAd: Ad;
  public _videAd: Ad;
  public _user: User;
  public _imgSafeResource: SafeResourceUrl;
  public _videoSafeResource: SafeResourceUrl;

  constructor(
    private _storageService: LocalStorageService,
    private _adService: AddsService,
    private _sanitizer: DomSanitizer,
    private _route: ActivatedRoute
  ) {
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this._textAd = this.generateEmptyAd();
    this._imgAd = this.generateEmptyAd();
    this._videAd = this.generateEmptyAd();
    // change add on page change
    this._route.url.subscribe((url) => {
      this.reloadAds();
    });
  }

  ngOnInit(): void {
    this.reloadAds();
  }

  public reloadAds() {
    this.getAds(1, 1);
    this.getAds(2, 2);
    this.getAds(3, 3);
  }

  public getAds(_type: number, _objToOverride: number) {
    this._adService.getRandomAd(this._user, _type).subscribe(
      (_success: Ad) => {
        switch (_objToOverride) {
          case 1:
            this._textAd = _success;
            break;
          case 2:
            this._imgAd = _success;
            this._imgSafeResource = this.getByPassLink(
              `${APIs.FILES_GIVER_CONTROLLER}?action=GET_AD_PIC&id=${this._imgAd.id}`
            );
            break;
          case 3:
            this._videAd = _success;
            this._videoSafeResource = this.getByPassLink(this._videAd.videoUrl);
            break;
          default:
            console.log('unknown');
        }
      },
      (_error: any) => {
        console.log('cannot get ad');
      }
    );
  }

  private generateEmptyAd(): Ad {
    return new Ad(0, 0, 0, 0, '', '', '', '', '', '', '', []);
  }

  private getByPassLink(_url: string): SafeResourceUrl {
    return this._sanitizer.bypassSecurityTrustResourceUrl(_url);
  }
}
