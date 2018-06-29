import {Component} from '@angular/core';
import {AuthService} from './auth/auth.service';
import {paths} from './paths';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  private paths: { login: string; auth: string; home: string; publication: string; welcome: string };

  constructor(private auth: AuthService) {
    this.paths = paths;
  }

  public isAuthenticated(): boolean {
    return this.auth.isAuthenticated();
  }
}
