export class User {
  constructor(
    public _email: string,
    public password: string,
    public type: number = 0,
    public description?: string
  ) {}
}
