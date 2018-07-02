import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {flatMap} from 'rxjs/operators';
import {UserInfo} from '../auth/auth.service';
import {User} from '../user';
import {environment} from '../../environments/environment';
import {Vehicle} from '../vehicles/vehicle';
import {of} from 'rxjs/observable/of';
import {Publication} from "../publication/publication";
import {Reservation} from '../reservation';

@Injectable()
export class BackendService {
  private base = environment.backendUrl;
  private loginUrl = this.base + 'users/login';
  private updateUserUrl = this.base + 'users/update';

  private addVehicleUrl = this.base + 'vehicles/';
  private userVehiclesUrl = this.base + "vehicles/user/";
  private deleteVehicleUrl = this.base + "vehicles/delete/";

  private publicationsUrl = this.base + "publication";
  private createPublicationUrl = this.base + "publication/create";
  private userPublicationsUrl = this.base + "publication/user/";

  private createReservationUrl = this.base + 'reservation/create';

  private http: HttpClient;
  public user$: Observable<User>;
  private userSubscriptions: Array<{ next, error }>;
  private publications$: Observable<Publication[]>;
  private publicationsSubscriptions: Array<{ next, error }>;
  private vehicles$: Observable<Vehicle[]>;

  constructor(http: HttpClient) {
    this.http = http;
    this.user$ = of(User.emptyUser());
    this.publications$ = of([]);
    this.userSubscriptions = [];
    this.publicationsSubscriptions = [];
  }

  private headers(): HttpHeaders {
    return new HttpHeaders()
      .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`);
  }

  login(userInfoObservable: Observable<UserInfo>) {
    this.user$ = flatMap((userInfo: UserInfo) => {
      return this.http.post<User>(this.loginUrl, userInfo, {
        headers: this.headers()
      });
    })(userInfoObservable);
  }

  getUser() {
    return this.user$;
  }

  getPublications(): Observable<Publication[]> {
    this.publications$ = flatMap((user: User) => {
      return this.http.get<Publication[]>(this.userPublicationsUrl + user.id, {
        headers: this.headers()
      })
    })(this.user$);

    return this.publications$;
  }

  getVehicles(): Observable<Vehicle[]> {
    this.vehicles$ = flatMap((user: User) => {
      return this.http.get<Vehicle[]>(this.userVehiclesUrl + user.id, {
        headers: this.headers()
      })
    })(this.user$);

    return this.vehicles$;
  }

  updateUser(user: User): Observable<User> {
    this.user$ = this.http.put<User>(this.updateUserUrl, user, {
      headers: this.headers()
    });

    return this.user$;
  }

  addVehicleToUser(newVehicle: Vehicle) {
    return flatMap((user: User) => {
        return this.http.post<Vehicle>(this.addVehicleUrl + user.id, newVehicle, {
          headers: this.headers()
        })
      }
    )(this.user$);
  }

  deleteVehicleFromUser(vehicleToDelete: Vehicle) {
    return flatMap((user: User) => {
        return this.http.delete<Vehicle>(this.deleteVehicleUrl + vehicleToDelete.id, {
          headers: this.headers()
        })
      }
    )(this.user$);
  }

  addPublicationToUser(publication: Publication) {
    return flatMap((user: User) => {
        return this.http.post<Publication>(this.createPublicationUrl, publication, {
            headers: this.headers()
          }
        )
      }
    )(this.user$);
  }

  submitReservation(reservation: Reservation): any {
    console.log(reservation);
    return this.http.post<Reservation>(this.createReservationUrl, reservation, {
      headers: this.headers()
    }).map(res => console.log(res));
  }
}
