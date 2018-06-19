import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PublicationService} from '../publication.service';
import {Publication} from '../publication';
import {ActivatedRoute, Router} from '@angular/router';
import 'rxjs/add/operator/filter';
import {SelectItem} from 'primeng/api';
import {UsersService} from '../../users/users.service';
import {User} from '../../user';


@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  styleUrls: ['./publication-create.component.css'],
  providers: [PublicationService, UsersService],
})

export class PublicationCreateComponent implements OnInit, OnDestroy {

  id: number;
  userId: number;
  availableDates: [string];
  publication: Publication;
  pickUpAddress: string;
  returnAddres: string;
  vehicles: SelectItem[];
  selectedVehicle: string;
  user: User;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private publicationService: PublicationService,
              private userService: UsersService) {

    this.vehicles = [
      {label: 'Select Vehicle', value: null},
      {label: 'Ford Ranger 2010 DDD111', value: '1'},
      {label: 'VW Gol 2015 FFF111', value: '2'},
      {label: 'Chevrolet Corsa 2017 GGG111', value: '3'},
    ];
    this.publication = Publication.emptyPublication();
  }

  ngOnInit() {
    this.route.queryParams
      .filter(params => params.userId)
      .subscribe(params => {
        this.userId = params.userId;
      });
  }

  ngOnDestroy(): void {
  }

  onNotifyPickUpAddress(pickUpAddress: string): void {
    this.publication.pickUpAddress = pickUpAddress;
  }

  onNotifyReturnAddress(returnAddress: string): void {
    this.publication.returnAddress.push(returnAddress);
  }

  publish() {
    this.userService.getUserById(this.userId).subscribe(user => this.publication.owner = user);
    console.log(this.publication);
    this.publicationService.savePublication(this.publication).subscribe(data => {alert('Succesfully Added Product details'); }, Error => {alert('failed while adding product details'); });
  }

  redirectPublicationPage() {
    this.router.navigate(['/publication']);
  }
}
