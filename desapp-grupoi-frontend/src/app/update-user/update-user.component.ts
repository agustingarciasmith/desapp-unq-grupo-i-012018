import {Component, OnInit} from '@angular/core';
import {UsersService} from '../users/users.service';
import {BackendService} from '../backend/backend.service';
import {ToasterConfig, ToasterService} from 'angular5-toaster/dist';
import {UserProfileErrors} from '../backend/error';
import {User} from '../user';
import {Vehicle} from 'app/vehicles/vehicle';
import {paths} from "../paths";

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
  private paths: { login: string; auth: string; home: string; publication: string; welcome: string };

  constructor(private service: BackendService, private toaster: ToasterService) {
    this.user = User.emptyUser();
    this.paths = paths
  }

  ngOnInit() {
    this.service.getUser().subscribe(
      (value: User) => {
        this.user = new User(
          value.id,
          value.cuil,
          value.name,
          value.address,
          value.email,
          value.vehicles,
          value.totalScore,
          value.avatar
        );
        console.log(value);
        this.loading = false;
      },
      error => {
        this.loading = false;
        this.error = true;
        console.log(error);
      });
  }

  updateUser() {
    this.loading = true;
    this.service.updateUser(this.userUpdate).subscribe(
      value => {
        this.loading = false;
        this.userSwitch = false;
        this.user = this.userUpdate;
      },
      error => {
        this.loading = false;
          this.handleUpdateError(error);
      });
  }

  modifyProfile() {
    this.userUpdate = this.user.copy();
    this.userSwitch = true;
  }

  cancelProfileUpdate() {
    this.userSwitch = false;
  }

  private handleUpdateError(error) {
    const errors = UserProfileErrors.from(error);
    if (errors.isInvalidRequest()) {
      this.toaster.pop('error', 'Error changing profile', 'Details: ' + errors.formatErors());
    } else {
      this.error = true;
    }
  }

  public toasterconfig: ToasterConfig =
    new ToasterConfig({
      showCloseButton: true,
      tapToDismiss: false,
      timeout: 0
    });
}
