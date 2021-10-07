import { Injectable } from '@angular/core';
import { User } from 'src/app/modules/Users/user.module';
import { LocalStorageRef } from './local-storage-ref.service';

@Injectable({ providedIn: 'root' })
export class LocalStorageService {
  private _localStorage: Storage;

  constructor(private _localStorageRef: LocalStorageRef) {
    this._localStorage = this._localStorageRef.getLocalStorage();
  }

  setItem(_data: any, _key: string) {
    this._localStorage.setItem(_key, JSON.stringify(_data));
  }

  clear() {
    this._localStorage.clear();
  }

  getData(_key: string) {
    return this._localStorage.getItem(_key);
  }

  removeData(_key: string) {
    this._localStorage.removeItem(_key);
  }
}
