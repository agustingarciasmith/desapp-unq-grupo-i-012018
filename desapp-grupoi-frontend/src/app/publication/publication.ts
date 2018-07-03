import {Vehicle} from "../vehicles/vehicle";
import {User} from "../user";

export class Publication {
  constructor(
    public publicationId: number,
    public userId: number,
    public vehicleId: number,
    public city: string,
    public pickUpAddress: string,
    public returnAddress: [string],
    public contactPhone: string,
    public availableDates: [string],
    public cost: number,
    public vehicle: Vehicle,
    public owner: User) {
  }

  public static emptyPublication() {
    return new Publication(
      null,
      null,
      null,
      "CUALQUIERA",
      null,
      [null],
      null,
      null,
      null,
      null,
      null);
  }
}
