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

  lat = 51.678418;
  lng = 7.809007;
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
        this.getCoordinates(publication.pickUpAddress);
      },
      err => {
        console.log(err);
      }
    );
    console.log(this.publication);
  }

  getCoordinates(address: string) {
    // this.publicationService.getCoordinates(address).subscribe({
    //   next: (response) => {
    //     this.lat = response[0].geometry.location.lat;
    //     this.lng = response[0].geometry.location.lng;
    //   },
    //   err: (err) => {
    //     console.log(err);
    //   }
    // });
  }
}
