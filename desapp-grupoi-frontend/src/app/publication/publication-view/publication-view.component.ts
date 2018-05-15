import { Component, OnInit } from '@angular/core';
import { PublicationService } from '../publication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-publication-view',
  templateUrl: './publication-view.component.html',
  styleUrls: ['./publication-view.component.css'],
  providers: [PublicationService]
})
export class PublicationViewComponent implements OnInit {

  lat = 51.678418;
  lng = 7.809007;
  private publication: any;
  formattedAddress: string;

  ngOnInit(): void {
    this.getPublication();
  }

  constructor(private router: Router,
    private publicationService: PublicationService) { }

  getPublication() {
    this.publicationService.findById(Number(window.location.pathname.slice(-1))).subscribe(
      publication => {
        this.publication = publication;
        this.getCoordinates(publication.pickUpAddress, publication.city);
      },
      err => {
        console.log(err);
      }
    );
    return this.publication;
  }

  getCoordinates(address: any, city: any) {
      this.publicationService.getCoordinates(address, city).subscribe(
        response => {
          this.lat = response.json().results[0].geometry.location.lat;
          this.lng = response.json().results[0].geometry.location.lng;
        },
        err => {
          console.log(err);
        }
      );
  }
}
