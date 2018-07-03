import {User} from './user';
import {Publication} from './publication/publication';

export class Reservation {
  public publicationId: number;
  public clientId: number;
  public reservationState: string;

  constructor(
    public publication: Publication,
    public client: User,
    public selectedDates: string[],
  ) {
    this.publicationId = publication.publicationId;
    this.clientId = client.id;
  }
}
