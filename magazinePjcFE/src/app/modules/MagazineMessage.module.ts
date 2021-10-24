export class MagazineMessage {
  constructor(public message: string, public magazine: Magazine) {}
}

export class MagazinePostMessage {
  constructor(public message: string, public post: MagazinePost) {}
}

export class MagazineCommentMessage {
  constructor(public message: string, public comment: MagazineComment) {}
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
    public tags: string[],
    public unlisted: boolean = false
  ) {}
}

export class MagazinePost {
  constructor(
    public id: number,
    public title: string,
    public dateString: string,
    public pdf: File | null,
    public magazine: string
  ) {}
}

export class MagazineComment {
  constructor(
    public id: number,
    public dateString: String,
    public text: string,
    public user: string,
    public magazine: string
  ) {}
}

export class MagazineLike {
  constructor(
    public dateString: string,
    public magazine: string,
    public user: string
  ) {}
}
