import { Magazine } from '../Magazine/Magazine.module';

export class MagazineMessage {
  constructor(public message: string, public magazine: Magazine) {}
}
