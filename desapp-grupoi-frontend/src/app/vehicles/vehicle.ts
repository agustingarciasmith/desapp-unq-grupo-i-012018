export class Vehicle {
  public static emptyVehicle() {
    return new Vehicle(null, null, null, null);
  }

  constructor(
    public numberOfPassengers: number,
    public type: string,
    public description: string,
    public license: string,
  ) {
  }

  copy(): Vehicle {
    return (
      new Vehicle(this.numberOfPassengers, this.type, this.description, this.license)
    );
  }
}
