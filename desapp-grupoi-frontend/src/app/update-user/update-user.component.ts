import { Component, OnInit } from '@angular/core';
import {UsersService} from '../users/users.service';
import {User} from '../users/user';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css'],
  providers: [UsersService]
})
export class UpdateUserComponent implements OnInit {

  constructor(private http: HttpClient, private user: User) {
  }
  userSwitch = false;
  user: User;

  constructor(private userService: UsersService) {}

  switchUserState() {
    this.userSwitch = !this.userSwitch;
  }

  ngOnInit() {
    this.getUser(1);
  }

  getUser(id: number) {
    this.userService.getUserById(id).subscribe(
      user => {
        this.user = user;
        console.log(this.user);
      },
      err => {
        console.log(err);
      }
    );
  }

  updateUser() {
    this.userService.updateUser(this.user);
    this.switchUserState();
  }
}
