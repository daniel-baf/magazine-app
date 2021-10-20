import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  Magazine,
  MagazineComment,
  MagazineCommentMessage,
  MagazineLike,
  MagazineMessage,
  MagazinePost,
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

  public getMagazineOwned(_username: String, _limit: number, _offset: number) {
    return this._http.get<Magazine[]>(
      `${APIs.MAGAZINE_CONTOLLER}?action=EDITOR_OWNED&editor=${_username}&limit=${_limit}&offset=${_offset}`
    );
  }

  public uploadPost(_postMessage: MagazinePostMessage, _file: File) {
    const _formData = new FormData();
    _formData.append('mag-post', JSON.stringify(_postMessage));
    _formData.append('datafile', _file, _file.name);
    return this._http.post<MagazinePostMessage>(
      `${APIs.MAGAZINE_POST_CONTROLLER}`,
      _formData
    );
  }

  public getPosts(
    _magazine: String,
    _limit: number,
    _offset: number
  ): Observable<MagazinePost[]> {
    return this._http.get<MagazinePost[]>(
      `${APIs.MAGAZINE_POST_CONTROLLER}?action=FOR_MAG&magazine=${_magazine}&limit=${_limit}&offset=${_offset}`
    );
  }

  public getBasicInfoToShowPost(_id: number): Observable<MagazinePost[]> {
    return this._http.get<MagazinePost[]>(
      `${APIs.MAGAZINE_POST_CONTROLLER}?action=SPECIFIC&id=${_id}`
    );
  }

  public getComments(
    _mag: string,
    _limit: number,
    _offset: number
  ): Observable<MagazineComment[]> {
    return this._http.get<MagazineComment[]>(
      `${APIs.MAGAZINE_REACTIONS_CONTROLLER}?action=GET_MAG_COMMENT&magazine=${_mag}&limit=${_limit}&offset=${_offset}`
    );
  }

  public commentMagazine(_comment: MagazineComment): Observable<string> {
    let _formData = new FormData();
    _formData.append('action', 'NEW_COMMENT');
    _formData.append('comment', JSON.stringify(_comment));
    return this._http.post(`${APIs.MAGAZINE_REACTIONS_CONTROLLER}`, _formData, {
      responseType: 'text',
    });
  }

  public leaveLike(_like: MagazineLike) {
    console.log('dejandol lik');
    let _formData = new FormData();
    _formData.append('action', 'LEAVE_LIKE');
    _formData.append('like', JSON.stringify(_like));
    return this._http.post(`${APIs.MAGAZINE_REACTIONS_CONTROLLER}`, _formData, {
      responseType: 'text',
    });
  }

  public getLikesCounter(_magazine: string): Observable<number> {
    return this._http.get<number>(
      `${APIs.MAGAZINE_REACTIONS_CONTROLLER}?action=GET_LIKES_COUNTER&magazine=${_magazine}`
    );
  }

  public getTags(): Observable<string[]> {
    return this._http.get<string[]>(
      `${APIs.MAGAZINE_REACTIONS_CONTROLLER}?action=GET_TAGS`
    );
  }
}
