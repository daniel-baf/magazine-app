export class User {
  constructor(
    public email: string,
    public password: string,
    public type: string = 'UNAUTHORIZED',
    public description?: string,
    public name: string = 'UNKNOWN'
  ) {}
}
