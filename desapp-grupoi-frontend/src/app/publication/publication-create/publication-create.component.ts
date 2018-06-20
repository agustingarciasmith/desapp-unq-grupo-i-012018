import {Component, OnDestroy, OnInit} from '@angular/core';
import {PublicationService} from '../publication.service';
import {Publication} from '../publication';
import {Router} from '@angular/router';
import 'rxjs/add/operator/filter';
import {UsersService} from '../../users/users.service';
import {User} from '../../user';
import {BackendService} from "../../backend/backend.service";
import {Vehicle} from "../../vehicles/vehicle";
import {paths} from "../../paths";


@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  styleUrls: ['./publication-create.component.css'],
  providers: [PublicationService, UsersService],
})

export class PublicationCreateComponent implements OnInit, OnDestroy {

  publication: Publication;
  vehicles: Vehicle[];
  selectedVehicle: Vehicle;
  private loading: boolean;

  constructor(private router: Router,
              private service: BackendService) {

    this.vehicles = [];
    this.publication = Publication.emptyPublication();
  }

  ngOnInit() {
    this.service.subscribeToUser((user: User) => {
      this.vehicles = user.vehicles;
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

  public createPublication() {
    this.loading = true;
    this.publication.vehicle = this.selectedVehicle;
    this.service.addPublicationToUser(this.publication);
    this.router.navigate([paths.home]);
  }

  redirectPublicationPage() {
    this.router.navigate(['/publication']);
  }

  setSelectedVechicle(value: string) {
    this.selectedVehicle = this.vehicles.find((vehicle: Vehicle) => {
      return vehicle.license == value;
    })
  }
}
