import {Vehicle} from '../vehicles/vehicle';
import {User} from '../user';

export class Publication {

  constructor(
    public id: number,
    public owner: User,
    public vehicle: Vehicle,
    public city: string,
    public pickUpAddress: string,
    public returnAddress: [string],
    public contactPhone: string,
    public availableDates: [string],
    public cost: number) {
  }

  public static emptyPublication() {
    return new Publication( null, null, null, 'CABA', null, [null], null, null, null);
  }
}
