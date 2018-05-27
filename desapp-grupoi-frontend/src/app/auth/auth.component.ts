import {AfterContentInit, Component, OnInit} from '@angular/core';
import {AuthService} from './auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit, AfterContentInit {

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  ngAfterContentInit() {
    this.authService.handleAuthentication();
  }
}
