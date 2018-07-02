export class Vehicle {
  public pictures: string[];

  public static emptyVehicle() {
    return new Vehicle(null, null, null, null, null);
  }

  constructor(
    public id: number,
    public numberOfPassengers: number,
    public type: string,
    public description: string,
    public license: string,
  ) {
    this.pictures = [];
  }

  copy(): Vehicle {
    return (
      new Vehicle(this.id, this.numberOfPassengers, this.type, this.description, this.license)
    );
  }

  addPicture(picture: string) {
    this.pictures.push(picture);
  }
}
