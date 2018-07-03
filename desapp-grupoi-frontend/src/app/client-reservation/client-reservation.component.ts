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

  constructor(private service: BackendService) {
    this.service.getClientReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      }
    );
  }

  ngOnInit() {
  }

}
