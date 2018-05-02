import { Injectable } from '@angular/core';

@Injectable()
export class Publication {



    id: number;
    city: string;
    pickUpAddress: string;
    returnAddress: string;
    contactPhone: string;
    cost: number;

    constructor(id: number, city: string, pickUpAdress: string, returnAddress: string, contactPhone: string, cost: number) {
        this.id = id;
        this.city = city;
        this.pickUpAddress = pickUpAdress;
        this.returnAddress = returnAddress;
        this.contactPhone = contactPhone;
        this.cost = cost;
    }
}
