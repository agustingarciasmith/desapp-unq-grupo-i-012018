import {Vehicle} from '../vehicles/vehicle';
import {User} from '../user';

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
