import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../user';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  constructor(private http: HttpClient, private user: User) {
  }
  userSwitch = false;
  rating = 4.8;

  switchUserState() {
    this.userSwitch = !this.userSwitch;
  }

  ngOnInit() {
  }

}
