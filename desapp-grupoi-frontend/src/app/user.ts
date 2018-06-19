import {Vehicle} from './vehicles/vehicle';

export class User {
  public static emptyUser() {
    return new User(null, null, null, null, null, [], null, null);
  }

  constructor(
    public id: string,
    public cuil: string,
    public name: string,
    public address: string,
    public email: string,
    public vehicles: Vehicle[],
    public totalScore: number,
    public avatar: string
  ) {
  }

  copy(): User {
    return (
      new User(this.id, this.cuil, this.name, this.address, this.email, this.vehicles, this.totalScore, this.avatar)
    );
  }

}
