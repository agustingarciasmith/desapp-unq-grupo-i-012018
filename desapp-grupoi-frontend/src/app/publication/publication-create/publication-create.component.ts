import {Component, OnDestroy, OnInit} from '@angular/core';
import {PublicationService} from '../publication.service';
import {Publication} from '../publication';
import {Router} from '@angular/router';
import 'rxjs/add/operator/filter';
import {UsersService} from '../../users/users.service';
import {BackendService} from "../../backend/backend.service";
import {Vehicle} from "../../vehicles/vehicle";
import {paths} from "../../paths";
import {User} from "../../user";


@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  styleUrls: ['./publication-create.component.css'],
  providers: [PublicationService, UsersService],
})

export class PublicationCreateComponent implements OnInit, OnDestroy {

  publication: Publication;
  vehicles: Vehicle[];
  selectedVehicleLicense: string;
  private loading: boolean;
  private user: User;

  constructor(private router: Router,
              private service: BackendService) {

    this.vehicles = [];
    this.publication = Publication.emptyPublication();
  }

  ngOnInit() {
    this.service.getVehicles().subscribe((vehicles: Vehicle[]) => {
      this.vehicles = vehicles;
    });

    this.service.getUser().subscribe((user: User) => {
      this.user = user;
    })
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
    this.publication.vehicleId = this.getSelectedVehicle().vehicleId;
    this.publication.userId = this.user.id;
    this.service.addPublicationToUser(this.publication).subscribe(
      (publication: Publication) => {
        this.loading = false;
        this.router.navigate([paths.home]);
      },
      error => {
        this.loading = false;
        alert(error);
      }
    );
  }

  redirectPublicationPage() {
    this.router.navigate(['/publication']);
  }

  getSelectedVehicle() {
    return this.vehicles.find((vehicle: Vehicle) => {
      return vehicle.license == this.selectedVehicleLicense;
    })
  }
}
