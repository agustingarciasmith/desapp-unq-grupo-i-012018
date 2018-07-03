import {Component, OnInit} from '@angular/core';
import {UsersService} from '../users/users.service';
import {BackendService} from '../backend/backend.service';
import {ToasterService} from 'angular5-toaster/dist';
import {UserProfileErrors} from '../backend/error';
import {Router} from '@angular/router';
import {User} from '../user';
import {paths} from '../paths';
import {Vehicle} from '../vehicles/vehicle';
import {Publication} from '../publication/publication';
import {Reservation} from '../reservation';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css'],
  providers: [UsersService]
})
export class UpdateUserComponent implements OnInit {

  user: User;
  userUpdate: User;
  vehicles: Vehicle[];
  newVehicle: Vehicle;
  actualVehicle: Vehicle;
  publications: Publication[];

  loading = true;
  error = false;
  userSwitch = false;
  paths: { login: string; auth: string; home: string; publication: string; welcome: string };
  dialogVehicle: boolean;
  dialogViewVehicle: boolean;
  reservations: Reservation[];
  clientScore: number;

  constructor(private service: BackendService, private toaster: ToasterService, private router: Router) {
    this.user = User.emptyUser();
    this.paths = paths;
    this.newVehicle = Vehicle.emptyVehicle();
    this.actualVehicle = Vehicle.emptyVehicle();
  }

  ngOnInit() {
    this.service.getUser().subscribe((value: User) => {
        this.user = User.from(value);
        this.loading = false;
        this.dialogVehicle = false;
        this.dialogViewVehicle = false;
        this.userSwitch = false;
        this.newVehicle = Vehicle.emptyVehicle();
      },
      error => {
        this.handleError(error);
      });

    this.service.getVehicles().subscribe((vehicles: Vehicle[]) => {
      this.vehicles = vehicles;
    });

    this.service.getPublications().subscribe((publications: Publication[]) => {
      this.publications = publications;
    });

    this.service.getOwnerReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      }
    );
  }

  updateUser() {
    this.loading = true;
    this.service.updateUser(this.userUpdate).subscribe(
      (user: User) => {
        this.user = User.from(user);
        this.loading = false;
        this.userSwitch = false;
      },
      error => {
        this.handleError(error);
      }
    );
  }

  modifyProfile() {
    this.userUpdate = this.user.copy();
    this.userSwitch = true;
  }

  cancelProfileUpdate() {
    this.userSwitch = false;
  }

  private handleError(error) {
    console.log(error);
    this.loading = false;
    const errors = UserProfileErrors.from(error);
    if (errors.isUnexpecterError()) {
      this.error = true;
    }
    if (errors.isInvalidRequest()) {
      alert('Error: ' + errors.formatErors());
    }
  }

  public addPublication() {
    this.router.navigate(['publication/create'], {queryParams: {userId: this.user.id}});
  }

  toggleAddVehicleDialog() {
    this.dialogVehicle = !this.dialogVehicle;
  }

  toggleViewVehicle() {
    this.dialogViewVehicle = !this.dialogViewVehicle;
  }

  addVehicleToUser() {
    this.loading = true;
    this.service.addVehicleToUser(this.newVehicle).subscribe(
      (vehicle: Vehicle) => {
        this.vehicles.push(vehicle);
        this.toggleAddVehicleDialog();
        this.loading = false;
      },
      error => {
        this.handleError(error);
      }
    );
  }

  viewVehicle(vehicle: Vehicle) {
    this.actualVehicle = vehicle;
    this.toggleViewVehicle();
  }

  deleteVehicle(deletedVehicle: Vehicle) {
    this.service.deleteVehicleFromUser(deletedVehicle).subscribe(
      () => {
        this.vehicles = this.vehicles.filter(vehicle => vehicle.vehicleId !== deletedVehicle.vehicleId);
      },
      error => {
        this.handleError(error);
      }
    );
  }

  vehicleLicenseOf(vehicleId: number) {
    if (this.vehicles) {
      return this.vehicles.find((vehicle: Vehicle) => {
        return vehicle.vehicleId === vehicleId;
      }).license;
    }
  }

  confirmReservation(reservationId) {
    this.service.confirmReservation(reservationId).subscribe(
      (_) => {
        this.getReservations();
      }
    );

  }

  confirmVehicleDeliver(id: number) {
    this.service.confirmVehicleDeliver(id).subscribe(
      (_) => {
        this.getReservations();
      }
    )
  }

  private getReservations() {
    this.service.getOwnerReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      }
    )
  }

  ownerFinishReserve(id: number) {
    this.service.ownerFinishReserve(id, this.clientScore).subscribe(
      (_) => {
        this.getReservations();
      }
    )
  }
}
