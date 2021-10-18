import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { APIs } from '../vars/enums/API';

@Injectable({
  providedIn: 'root',
})
export class FileGiverService {
  constructor(private _http: HttpClient) {}

  public getPdf(_id: number) {
    const url = `${APIs.FILES_GIVER_CONTROLLER}?action=SHOW_PDF&id=${_id}`;
    this._http.get(url).subscribe((_success: any) => {
      console.log(_success);
    });
  }
}
