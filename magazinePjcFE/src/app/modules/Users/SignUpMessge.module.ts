import { User } from './user.module';

export class SignUpMessage {
  constructor(public message: string, public user: User) {}
}
