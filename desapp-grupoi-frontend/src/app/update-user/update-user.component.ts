import { Component, OnInit } from '@angular/core';
import {UsersService} from '../users/users.service';
import {User} from '../users/user';
import {BackendService} from '../backend/backend.service';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css'],
  providers: [UsersService]
})
export class UpdateUserComponent implements OnInit {

  userSwitch = false;
  user$: Observable<User>;

  constructor(private service: BackendService) {
  }

  switchUserState() {
    this.userSwitch = !this.userSwitch;
  }

  ngOnInit() {
    this.user$ = this.service.getUser()
  }

  updateUser() {
    this.switchUserState();
  }
}
