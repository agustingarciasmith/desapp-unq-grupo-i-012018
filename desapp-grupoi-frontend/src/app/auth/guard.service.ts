import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';
import {Observable} from 'rxjs/Observable';
import {paths} from "../paths";

@Injectable()
export class GuardService implements CanActivate {

  constructor(public auth: AuthService, public router: Router) {
    this.auth = auth;
    this.router = router;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.auth.isAuthenticated()) {
      this.router.navigate([paths.login], {
        queryParams: {
          return: state.url
        }
      });
      return false;
    } else {
      return true;
    }
  }

}
