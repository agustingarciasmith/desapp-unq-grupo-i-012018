import {Vehicle} from '../vehicle';

export interface User {
  id: string;
  cuil: string;
  name: string;
  address: string;
  email: string;
  vehicles: Vehicle[];
  score: number;
}
