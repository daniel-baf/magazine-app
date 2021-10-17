export class SubscriptionMag {
  constructor(
    public id: number,
    public months: number,
    public expirationDateString: string,
    public acquisitionDateString: string,
    public magazine: string,
    public reader: string
  ) {}
}
