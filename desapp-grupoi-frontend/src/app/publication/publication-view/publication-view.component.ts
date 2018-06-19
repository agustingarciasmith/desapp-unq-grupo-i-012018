import {Component, OnInit} from '@angular/core';
import {PublicationService} from '../publication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-publication-view',
  templateUrl: './publication-view.component.html',
  styleUrls: ['./publication-view.component.css'],
  providers: [PublicationService]
})
export class PublicationViewComponent implements OnInit {

  latpu = 51.678418;
  lngpu = 7.809007;
  latr = 51.678418;
  lngr = 7.809007;
  public publication: any;

  ngOnInit(): void {
    this.getPublication();
  }

  constructor(private router: Router,
              private publicationService: PublicationService) {
  }

  getPublication() {
    this.publicationService.findById(Number(window.location.pathname.slice(-1))).subscribe(
      publication => {
        this.publication = publication;
        this.getCoordinatesPickUp(publication.pickUpAddress);
        this.getCoordinatesReturn(publication.returnAddress[0]);
      },
      err => {
        console.log(err);
      }
    );
    console.log(this.publication);
  }

  getCoordinatesPickUp(address: string) {
    this.publicationService.getCoordinates(address).subscribe(
      response => {
        console.log(response);
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
        console.log(response);
        this.latr = response.results[0].geometry.location.lat;
        this.lngr = response.results[0].geometry.location.lng;
      },
      err => {
        console.log(err);

      });
  }
}
