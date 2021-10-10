import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MagazineMessage } from 'src/app/modules/Messages/MagazineMessage.module';
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
}
