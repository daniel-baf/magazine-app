export class User {
  constructor(
    public _email: string,
    public _password: string,
    public _type: string = 'UNAUTHORIZED',
    public _description?: string
  ) {}
}
