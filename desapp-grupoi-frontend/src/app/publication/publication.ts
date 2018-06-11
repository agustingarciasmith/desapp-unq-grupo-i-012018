import {User} from '../users/user';
import {Vehicle} from '../vehicles/vehicle';

export interface Publication {
    id: number;
    owner: User;
    vehicle: Vehicle;
    city: string;
    pickUpAddress: string;
    returnAddress: [string];
    contactPhone: string;
    availableDates: [string];
    cost: number;
}
