import { Injectable } from '@angular/core';

function getLocalStorage() {
  return localStorage;
}

// CREATE A GLOBAL LOCAL STORAGE USING SERVICES
@Injectable({
  providedIn: 'root',
})
export class LocalStorageRef {
  constructor() {}

  // RETURN STORAGE
  getLocalStorage() {
    return getLocalStorage();
  }
}
