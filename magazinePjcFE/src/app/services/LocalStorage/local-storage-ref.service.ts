import { Injectable } from '@angular/core';

function getLocalStorage() {
  return localStorage;
}

@Injectable({
  providedIn: 'root',
})

// CREATE A GLOBAL LOCAL STORAGE USING SERVICES
export class LocalStorageRef {
  constructor() {}

  // RETURN STORAGE
  getLocalStorage() {
    return getLocalStorage();
  }
}
