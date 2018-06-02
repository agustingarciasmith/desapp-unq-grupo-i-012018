import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  userSwitch = false;
  rating = 4.8;

  constructor() { }

  switchUserState() {
    this.userSwitch = !this.userSwitch;
  }



  ngOnInit() {
  }

}
