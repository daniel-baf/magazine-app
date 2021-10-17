export class MagazineMessage {
  constructor(public message: string, public magazine: Magazine) {}
}

export class MagazinePost {
  constructor(
    public id: number,
    public title: string,
    public dateString: String,
    public pdf: File,
    public magazine: string
  ) {}
}

export class PostMessage {
  constructor(public message: string, public post: MagazinePost) {}
}

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
