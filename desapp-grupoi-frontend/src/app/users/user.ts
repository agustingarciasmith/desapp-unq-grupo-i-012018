import {Vehicle} from '../vehicles/vehicle';

export interface User {
  id: string;
  cuil: string;
  name: string;
  address: string;
  email: string;
  avatar: string;
  vehicles: Vehicle[];
  score: number;
}
