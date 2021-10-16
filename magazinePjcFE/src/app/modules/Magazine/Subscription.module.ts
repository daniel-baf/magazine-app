export class SubscriptionMag {
  constructor(
    private id: number,
    private months: number,
    private expirationDateString: string,
    private acquisitionDateString: string,
    private magazine: string,
    private reader: string
  ) {}
}
