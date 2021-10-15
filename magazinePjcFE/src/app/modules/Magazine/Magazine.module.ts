export class Magazine {
  constructor(
    public name: string,
    public mensuality: number,
    public companyFee: number,
    public costPerDay: number,
    public dateString: string,
    public description: string,
    public allowComment: boolean,
    public allowLikes: boolean,
    public category: string,
    public editor: string,
    public approved: boolean,
    public tags: string[]
  ) {}
}
