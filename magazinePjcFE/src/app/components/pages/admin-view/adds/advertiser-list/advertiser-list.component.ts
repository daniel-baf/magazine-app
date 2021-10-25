import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Ad } from 'src/app/modules/AdsMessages.module';
import { AddsService } from 'src/app/services/Financial/ads.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-advertiser-list',
  templateUrl: './advertiser-list.component.html',
  styleUrls: ['./advertiser-list.component.css'],
})
export class AdvertiserListComponent implements OnInit {
  // page
  public _advertisers: Array<string>;
  public _activeAdvertiser: string;
  public _newAdForm: FormGroup;
  // for adds
  public _tags: string[];
  public _tagsForAd: Array<string>;
  public _fileToUpload: File | null;
  // alerts
  public _showAlertMsg: boolean = false;
  public _showSuccessMsg: boolean = false;
  public _alertMsg: string;
  public _successMsg: string;
  // INIFINITE SCROLL
  public _actualPage: number;
  public _offset: number;
  public _limit: number = 20;
  public _finishPage: number = 40;
  public _showScrollHeight: number = 400;
  public _hideScrollHeight: number = 200;
  public _showGoUpButton: boolean;

  constructor(
    private _adsService: AddsService,
    private _magService: MagazineService
  ) {
    this._advertisers = new Array<string>();
    this._tagsForAd = new Array<string>();
    this._offset = 0;
    this._actualPage = 1;
    this._newAdForm = this.getFormGroup();
  }

  ngOnInit(): void {
    this.getTags();
    this.getAdvertisers();
  }

  // FOFR HTML
  public resetForm() {
    this._newAdForm = this.getFormGroup();
  }

  public createAdd() {
    if (this._newAdForm.valid && this.isValid2()) {
      // create add
      let _ad: Ad = new Ad(
        0,
        0,
        +this._newAdForm.controls['_type'].value,
        this._newAdForm.controls['_ammount'].value,
        this._newAdForm.controls['_expirationDate'].value,
        this._newAdForm.controls['_startDate'].value,
        this._newAdForm.controls['_advertiser'].value,
        '',
        this._newAdForm.controls['_videoUrl'].value,
        this._newAdForm.controls['_img'].value,
        this._newAdForm.controls['_text'].value,
        this._tagsForAd
      );
      // call service
      this._adsService.registAd(_ad, this._fileToUpload).subscribe(
        (_success: string) => {
          if (_success === 'NO_ERROR') {
            this.showSuccessMessage('Se ha generado el nuevo anuncio');
          } else {
            this.showAlertMessage(
              'ha ocurrido un error durante la creacion de un nuevo anuncio'
            );
          }
        },
        (_error: Error) => {
          console.log('no chona');
          console.log(_error);
        }
      );
    } else {
      this.showAlertMessage(
        'no has ingresado uno/os de los atributos, todos sob obligatorios'
      );
    }
  }

  // FOR HTML
  public setImg(_event: Event) {
    const files = (_event.target as HTMLInputElement).files;
    if (files != null) {
      this._fileToUpload = files.item(0);
    }
  }

  public addTagForAd() {
    if (this._tagsForAd.indexOf(this._newAdForm.controls['_tags'].value) < 0) {
      this._tagsForAd.push(this._newAdForm.controls['_tags'].value);
    } else {
      this.showAlertMessage('La categoria ya esta agregada');
    }
  }

  public removeTagForAd(_tag: string) {
    this._tagsForAd.splice(this._tagsForAd.indexOf(_tag), 1);
  }

  public isValid2(): boolean {
    if (this._tagsForAd.length === 0) {
      return false;
    }
    if (
      this._newAdForm.controls['_type'].value == '2' && // 2 = text-image
      this._newAdForm.controls['_img'].value == ''
    ) {
      return false;
    } else if (
      this._newAdForm.controls['_type'].value == '3' && // 3 = text-video
      this._newAdForm.controls['_videoUrl'].value == ''
    ) {
      return false;
    }
    return true;
  }

  // FOR TS workout
  private getAdvertisers() {
    this._adsService
      .getAdvertisers(this._limit, this._offset)
      .subscribe((_success: string[]) => {
        for (const _advertiser of _success) {
          this._advertisers.push(_advertiser);
        }
        this._offset += +this._limit;
      });
  }

  private getTags() {
    this._magService.getTags().subscribe((_success: string[]) => {
      this._tags = _success;
    });
  }

  private showAlertMessage(_message: string) {
    this._alertMsg = _message;
    this._showAlertMsg = true;
  }
  private showSuccessMessage(_message: string) {
    this._successMsg = _message;
    this._showSuccessMsg = true;
  }

  public setActiveAdvertiser(_advertiser: string) {
    this._activeAdvertiser = _advertiser;
    this._newAdForm.controls['_advertiser'].setValue(_advertiser);
    this._tagsForAd = [];
    this._showAlertMsg = false;
    this._showSuccessMsg = false;
  }

  private getFormGroup(): FormGroup {
    return new FormGroup({
      _ammount: new FormControl('', [Validators.required]),
      _advertiser: new FormControl('', [Validators.required]),
      _expirationDate: new FormControl('', [Validators.required]),
      _startDate: new FormControl('', [Validators.required]),
      _type: new FormControl('', [Validators.required]),
      _videoUrl: new FormControl(''),
      _img: new FormControl(''),
      _text: new FormControl('', [Validators.required]),
      _tags: new FormControl(''),
    });
  }

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
