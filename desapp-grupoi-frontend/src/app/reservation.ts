import {User} from './user';
import {Publication} from './publication/publication';

export class Reservation {
  constructor(
    public publication: Publication,
    public client: User,
    public dates: Date[]
  ) {
  }
}
