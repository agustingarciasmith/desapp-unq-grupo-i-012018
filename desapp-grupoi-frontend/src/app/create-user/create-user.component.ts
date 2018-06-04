import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UsersService} from '../users/users.service';
import {User} from '../users/user';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css'],
  providers: [UsersService]
})

export class CreateUserComponent implements OnInit {
  public user: User;
  private postStatus: any;

  constructor(private http: HttpClient, private userService: UsersService) {
    this.user = {
      id: '',
      name: '',
      address: '',
      cuil: '',
      email: '',
    };
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.postUser(this.user);
  }
}
