import { Component, OnInit } from '@angular/core';
import {BackendService} from "../backend/backend.service";
import {Reservation} from "../reservation";

@Component({
  selector: 'app-client-reservation',
  templateUrl: './client-reservation.component.html',
  styleUrls: ['./client-reservation.component.css']
})
export class ClientReservationComponent implements OnInit {
  reservations: Reservation[] = [];
  clientScore = 0;

  constructor(private service: BackendService) {
    this.service.getClientReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      }
    );
  }

  ngOnInit() {
  }

  clientGetVehicle(id: number) {
    this.service.clientGetVehicle(id).subscribe(
      (_) => {
        this.getReservations();
      }
    )
  }

  private getReservations() {
    this.service.getClientReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      }
    )
  }

  returnVehicle(id: number) {
    this.service.clientReturnVehicle(id, this.clientScore).subscribe(
      (_) => {
        this.getReservations();
      }
    )
  }
}
