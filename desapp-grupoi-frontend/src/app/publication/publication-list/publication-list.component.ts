import {Component, OnInit} from '@angular/core';
import {PublicationService} from '../publication.service';
import {Router} from '@angular/router';
import {OrderPipe} from 'ngx-order-pipe';
import {BackendService} from "../../backend/backend.service";
import {Publication} from "../publication";

@Component({
  selector: 'app-publication-list',
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.css'],
  providers: [PublicationService]
})
export class PublicationListComponent implements OnInit {
  order = 'owner.totalScore';
  reverse = false;
  public publications: any[];
  public sortedPublications: any[];
  public p = 1;

  constructor(private router: Router, private service: BackendService) {
    this.service.getAllPublications().subscribe((publications: Publication[]) => {
      this.publications = publications;
    })
  }

  ngOnInit() {
  }

  redirectNewPublicationPage() {
    this.router.navigate(['/publication/create']);
  }

  viewPublicationPage(publication: any) {
    if (publication) {
      this.router.navigate(['/publication', publication.id]);
    }
  }

  setOrder(value: string) {
    if (this.order === value) {
      this.reverse = !this.reverse;
    }
    this.order = value;
  }
}
