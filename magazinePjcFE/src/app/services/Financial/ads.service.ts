import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ad } from 'src/app/modules/AdsMessages.module';
import { User } from 'src/app/modules/SignUpMessge.module';
import { APIs } from 'src/app/vars/enums/API';

@Injectable({
  providedIn: 'root',
})
export class AddsService {
  constructor(private _http: HttpClient) {}

  public createAdvertiser(_advertiser: string): Observable<string> {
    const _formData = new FormData();
    _formData.append('advertiser', _advertiser);
    _formData.append('sub-action', 'new-advertiser');
    return this._http.post(`${APIs.ADS_CONTROLLER}`, _formData, {
      responseType: 'text',
    });
  }

  public getAdvertisers(_limit: number, _offset: number): Observable<string[]> {
    return this._http.get<string[]>(
      `${APIs.ADS_CONTROLLER}?sub-action=ADVERTISERS&limit=${_limit}&offset=${_offset}&action=GET_ADVERTISERS`
    );
  }

  public registAd(_ad: Ad, _file: File | null): Observable<string> {
    let _form = new FormData();
    _form.append('sub-action', 'new-add');
    _form.append('ad', JSON.stringify(_ad));
    if (_file != null) {
      _form.append('file', _file, _file.name);
    }
    console.log('enviando');
    console.log(_ad);

    return this._http.post(`${APIs.ADS_CONTROLLER}`, _form, {
      responseType: 'text',
    });
  }

  public getRandomAd(_user: User, _adType: number): Observable<Ad> {
    // _adType -> 1 = text, 2 => img and text 3 -> video
    return this._http.get<Ad>(
      `${APIs.ADS_CONTROLLER}?sub-action=get-ad&ad-type=${_adType}&user=${_user.email}`
    );
  }
}
