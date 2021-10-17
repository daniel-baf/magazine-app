import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  SubscriptionMag,
  SubscriptionMessage,
} from 'src/app/modules/SubscriptionMessage.module';
import { APIs } from 'src/app/vars/enums/API';

@Injectable({ providedIn: 'root' })
export class SubscriptionService {
  constructor(private _http: HttpClient) {}

  public registNewSub(
    _subscription: SubscriptionMag
  ): Observable<SubscriptionMessage> {
    console.log('enviando');
    console.log(_subscription);
    console.log('----------------------------------');

    return this._http.post<SubscriptionMessage>(
      APIs.SUBSCRIPTION_CONTOLLER,
      _subscription
    );
  }
}
