import {Component, OnInit} from '@angular/core';
import {BackendService} from '../backend/backend.service';
import {AuthService} from '../auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private backend: BackendService, private auth: AuthService) {
    this.backend = backend;
  }

  ngOnInit() {
    this.backend.login(this.auth.getUserInfo())
  }
}
