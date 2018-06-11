import {Vehicle} from '../vehicles/vehicle';

export class User {
  constructor(
    private id: string,
    private cuil: string,
    private name: string,
    private address: string,
    private email: string,
    private vehicles: Vehicle[],
    private score: number,
    private avatar: string
  ) {

  }
}
