import { MagazineComment, MagazineLike } from './MagazineMessage.module';
import { SubscriptionMag } from './SubscriptionMessage.module';

export class MagazineCommentsReport {
  constructor(public magazine: string, public comments: MagazineComment[]) {}
}

export class MagazineSubscriptionReport {
  constructor(
    public magazine: string,
    public subscriptions: SubscriptionMag[]
  ) {}
}

export class MagazineLikesReport {
  constructor(public magazine: string, public likes: MagazineLike[]) {}
}
