import {Component, OnInit} from '@angular/core';
import {PublicationService} from '../publication.service';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../user';
import {BackendService} from '../../backend/backend.service';
import {UsersService} from '../../users/users.service';
import {Publication} from "../publication";
import {Vehicle} from "../../vehicles/vehicle";
import {paths} from "../../paths";


@Component({
  selector: 'app-publication-view',
  templateUrl: './publication-view.component.html',
  styleUrls: ['./publication-view.component.css'],
  providers: [PublicationService, BackendService, UsersService]
})
export class PublicationViewComponent implements OnInit {

  latpu = 51.678418;
  lngpu = 7.809007;
  latr = 51.678418;
  lngr = 7.809007;
/*public publication: any;
  public unavailableDates: Date[];
  public selectedDates: Date[] = [];
  public userId = 88;
  public user: User;
*/
  publication: Publication = Publication.emptyPublication();
  owner: User = User.emptyUser();
  vehicle: Vehicle = Vehicle.emptyVehicle();
  selectedDate: string = null;

  ngOnInit(): void {
  }

  constructor(private router: Router,
              private route: ActivatedRoute,
              private service: BackendService,
              private publicationService: PublicationService) {

    this.route.params.subscribe(params => {
      this.service.getPublication(params.id).subscribe((publication: Publication) => {
        this.publication = publication;
        this.owner = publication.owner;
        this.vehicle = publication.vehicle;
        this.getCoordinatesPickUp(publication.pickUpAddress);
        this.getCoordinatesReturn(publication.returnAddress[0]);
      })
    })
  }

  getCoordinatesPickUp(address: string) {
    this.publicationService.getCoordinates(address).subscribe(
      response => {
        this.latpu = response.results[0].geometry.location.lat;
        this.lngpu = response.results[0].geometry.location.lng;
      },
      err => {
        console.log(err);
      });
  }

  getCoordinatesReturn(address: string) {
    this.publicationService.getCoordinates(address).subscribe(
      response => {
        this.latr = response.results[0].geometry.location.lat;
        this.lngr = response.results[0].geometry.location.lng;
      },
      err => {
        console.log(err);

      });
  }
/*private setAvailableDates() {
    const monthDates = Array.from(Array(32).keys()).map((v, i) => `2018-07-${i}`);
    const stringDates = monthDates.filter(item => this.publication.availableDates.indexOf(item) < 0).slice(1);
    this.unavailableDates = stringDates.map(function (date) {
      return new Date(date);
    });
    console.log(this.unavailableDates.toString());
  }*/
  submitReservation() {
    this.service.makeReservation(this.publication, [this.selectedDate]).subscribe(
      (_) => {
        this.router.navigate([paths.clientReservations]);
      },
      error => {
        alert(error);
      }
    );
  }
}
