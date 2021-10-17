import { SubscriptionMag } from '../Magazine/Subscription.module';

export class SubscriptionMessage {
  constructor(public subscription: SubscriptionMag, public message: string) {}
}
