export class Vehicle {
  public pictures: string[];

  public static emptyVehicle() {
    return new Vehicle(null, null, null, null, null, null);
  }

  constructor(
    public vehicleId: number,
    public userId: number,
    public numberOfPassengers: number,
    public vehicleType: string,
    public description: string,
    public license: string,
  ) {
    this.pictures = [];
  }

  copy(): Vehicle {
    return (
      new Vehicle(this.vehicleId, this.userId, this.numberOfPassengers, this.vehicleType, this.description, this.license)
    );
  }

  addPicture(picture: string) {
    this.pictures.push(picture);
  }
}
