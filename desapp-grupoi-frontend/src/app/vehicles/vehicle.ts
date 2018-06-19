export class Vehicle {
  public pictutres: string[];

  public static emptyVehicle() {
    return new Vehicle(null, null, null, null);
  }

  constructor(
    public numberOfPassengers: number,
    public type: string,
    public description: string,
    public license: string,
  ) {
    this.pictutres = [];
  }

  copy(): Vehicle {
    return (
      new Vehicle(this.numberOfPassengers, this.type, this.description, this.license)
    );
  }

  addPicture(picture: string) {
    this.pictutres.push(picture);
  }
}
