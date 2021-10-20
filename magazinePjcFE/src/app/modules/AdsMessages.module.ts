export class Ad {
  constructor(
    public id: number,
    public shownCounter: number,
    public type: number, // 1 = text, 2 = text and img 3 = video and text
    public advertiserPaid: number,
    public expirationDateString: string,
    public startDateString: string,
    public advertiser: string,
    public shownUrl: string,
    public videoUrl: string,
    public imgLocalPath: string,
    public text: string,
    public tags: string[]
  ) {}
}
