import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router) { }

  ngOnInit() {
    if(this.auth.isAuthenticated()) {
      this.router.navigate(["/home"])
    }
  }

}
