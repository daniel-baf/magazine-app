import { User } from "../Users/user.module";


export class SignUpMessage {
  constructor(public message: string, public user: User) {}
}
