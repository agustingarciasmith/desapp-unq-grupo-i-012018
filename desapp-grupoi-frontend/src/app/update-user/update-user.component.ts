import {Component, OnInit} from '@angular/core';
import {UsersService} from '../users/users.service';
import {BackendService} from '../backend/backend.service';
import {ToasterService} from 'angular5-toaster/dist';
import {UserProfileErrors} from '../backend/error';
import {User} from '../user';
import {paths} from "../paths";
import {Vehicle} from "../vehicles/vehicle";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css'],
  providers: [UsersService]
})
export class UpdateUserComponent implements OnInit {

  loading = true;
  error = false;
  userSwitch = false;
  user: User;
  userUpdate: User;
  paths: { login: string; auth: string; home: string; publication: string; welcome: string };
  newVehicle: Vehicle;
  dialogVehicle: boolean;

  constructor(private service: BackendService, private toaster: ToasterService) {
    this.user = User.emptyUser();
    this.paths = paths;
    this.newVehicle = Vehicle.emptyVehicle();
  }

  ngOnInit() {
    this.service.subscribeToUser(
      (value: User) => {
        this.user = User.from(value);
        this.loading = false;
        this.dialogVehicle = false;
        this.userSwitch = false;
        this.newVehicle = Vehicle.emptyVehicle()
      },
      error => {
        this.handleError(error);
      });
  }

  updateUser() {
    this.loading = true;
    this.service.updateUser(this.userUpdate)
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
    if(errors.isInvalidRequest()) {
      alert('Error: ' + errors.formatErors());
    }
  }

  toggleAddVehicleDialog() {
    this.dialogVehicle = !this.dialogVehicle;
  }

  addPicture(picture: string) {
      this.newVehicle.addPicture(picture);
  }

  addVehicleToUser() {
    this.loading = true;
    this.service.addVehicleToUser(this.newVehicle);
    this.toggleAddVehicleDialog();
  }

  addVehicle() {
    this.toggleAddVehicleDialog();
  }
}
