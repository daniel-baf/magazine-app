import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  Magazine,
  MagazineMessage,
  MagazinePostMessage,
} from 'src/app/modules/MagazineMessage.module';
import { APIs } from 'src/app/vars/enums/API';

@Injectable({ providedIn: 'root' })
export class MagazineService {
  constructor(private _http: HttpClient) {}

  public queueOrPublishMagazine(
    _magazineMessage: MagazineMessage
  ): Observable<MagazineMessage> {
    return this._http.post<MagazineMessage>(
      APIs.MAGAZINE_CONTOLLER,
      _magazineMessage
    );
  }

  public getNoPublishedMags(_cuantity: number): Observable<Magazine[]> {
    return this._http.get<Magazine[]>(
      `${APIs.MAGAZINE_CONTOLLER}?action=NO_PUBLISHED&cuantity=${_cuantity}`
    );
  }

  public getMagazineForUser(
    _limit: number,
    _offset: number,
    _reader: string
  ): Observable<Magazine[]> {
    return this._http.get<Magazine[]>(
      `${APIs.MAGAZINE_CONTOLLER}?action=USER_INTEREST&limit=${_limit}&offset=${_offset}&reader=${_reader}`
    );
  }

  public updateMagazine(
    _magazineMessage: MagazineMessage
  ): Observable<MagazineMessage> {
    return this._http.post<MagazineMessage>(
      APIs.MAGAZINE_CONTOLLER,
      _magazineMessage
    );
  }

  public getMagazine(_magName: string): Observable<Magazine[]> {
    return this._http.get<Magazine[]>(
      `${APIs.MAGAZINE_CONTOLLER}?action=ONE&mag-name=${_magName}`
    );
  }

  public uploadPost(_postMessage: MagazinePostMessage, _file: File) {
    console.log('enviando');
    console.log(_postMessage);

    const _formData = new FormData();
    console.log(JSON.stringify(_postMessage));

    _formData.append('mag-post', JSON.stringify(_postMessage));
    _formData.append('datafile', _file, _file.name);
    return this._http.post<MagazinePostMessage>(
      `${APIs.MAGAZINE_POST_CONTROLLER}`,
      _formData
    );
  }
}
