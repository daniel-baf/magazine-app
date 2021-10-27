import { Ad } from './AdsMessages.module';
import {
  Magazine,
  MagazineComment,
  MagazineLike,
} from './MagazineMessage.module';
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

export class EditorEarningReport {
  constructor(public magazine: string, public subs: EditorSubWithEntry[]) {}
}

export class EditorSubWithEntry {
  constructor(public fee: number, public subscription: SubscriptionMag) {}
}

export class CompanyEarningByMag {
  constructor(
    public magazine: Magazine,
    public subscriptions: EditorSubWithEntry[]
  ) {}
}

export class EarningByAdvertiser {
  constructor(public advertiser: string, public ads: Ad[]) {}
}

export class EarningsResultReport {
  constructor(
    public type: string,
    public entry: number,
    public loss: number,
    public date: Date
  ) {}
}
