export class Magazine {
  constructor(
    private name: string,
    private mensuality: number,
    private companyFee: number,
    private costPerDay: number,
    private date: Date,
    private description: string,
    private allowComment: boolean,
    private allowLikes: boolean,
    private category: string,
    private editor: string,
    private approved: boolean
  ) {}
}
