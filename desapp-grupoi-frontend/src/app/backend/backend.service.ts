import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {flatMap} from 'rxjs/operators';
import {AuthService, UserInfo} from '../auth/auth.service';
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
  private getUserUrl = this.base + 'users/';

  private addVehicleUrl = this.base + 'vehicles/create';
  private userVehiclesUrl = this.base + "vehicles/user/";

  private deleteVehicleUrl = this.base + "vehicles/delete/";
  private createPublicationUrl = this.base + "publication/create";
  private userPublicationsUrl = this.base + "publication/user/";
  private allPublicationsUrl = this.base + "publication/all";

  private publicationUrl = this.base + "publication/";
  private createReservationUrl = this.base + 'reservation/create';
  private clientReservationsUrl = this.base + 'reservation/client/';

  private owmerReservationsUrl = this.base + 'reservation/owner/';
  private http: HttpClient;
  public user$: Observable<User>;
  private userPublications$: Observable<Publication[]>;
  private vehicles$: Observable<Vehicle[]>;
  private publications$: Observable<Publication[]>;
  private auth: AuthService;

  constructor(http: HttpClient, auth: AuthService) {
    this.http = http;
    this.user$ = of(User.emptyUser());
    this.userPublications$ = of([]);
    this.auth = auth;
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

    this.user$.subscribe(
      (user: User) => {
        localStorage.setItem('userId', user.id.toString());
        this.user$ = this.getUser();
      }
    )
  }

  getUser() {
    let userId = localStorage.getItem("userId");
    if(userId){
      this.user$ = this.http.get<User>(this.getUserUrl + userId, {
        headers:this.headers()
      });
    }else{
      this.login(this.auth.getUserInfo());
    }
    return this.user$;
  }

  getPublications(): Observable<Publication[]> {
    this.userPublications$ = flatMap((user: User) => {
      return this.http.get<Publication[]>(this.userPublicationsUrl + user.id, {
        headers: this.headers()
      })
    })(this.user$);

    return this.userPublications$;
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
        newVehicle.userId = user.id;
        return this.http.post<Vehicle>(this.addVehicleUrl, newVehicle, {
          headers: this.headers()
        })
      }
    )(this.user$);
  }

  deleteVehicleFromUser(vehicleToDelete: Vehicle) {
    return flatMap((user: User) => {
        return this.http.delete(this.deleteVehicleUrl + vehicleToDelete.vehicleId, {
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

  getAllPublications(): Observable<Publication[]> {
    this.publications$ = this.http.get<Publication[]>(this.allPublicationsUrl, {
      headers: this.headers()
    });

    return this.publications$;
  }

  getPublication(id: number) {
    return this.http.get<Publication>(this.publicationUrl + id, {
      headers: this.headers()
    })
  }

  makeReservation(publication: Publication, selectedDates: string[]) {
    return flatMap((user: User) => {
      let reservation = new Reservation(publication, user, selectedDates, null);
      reservation.publication = Publication.emptyPublication();
      return this.http.post(this.createReservationUrl, reservation, {
        headers: this.headers()
      })
    })(this.getUser());
  }

  getClientReservations() {
    return flatMap((user: User) => {
      return this.http.get<Reservation[]>(this.clientReservationsUrl + user.id,{
        headers: this.headers()
      })
    })(this.getUser());
  }

  getOwnerReservations() {
    return flatMap((user: User) => {
      return this.http.get<Reservation[]>(this.owmerReservationsUrl + user.id,{
        headers: this.headers()
      })
    })(this.getUser());
  }

  confirmReservation(reservationId: number) {
    return flatMap((user: User) => {
      return this.http.put((this.base + "reservation/owner/" + user.id + "/confirm/" + reservationId), {}, {
        headers: this.headers()
      })
    })(this.getUser());
  }

  clientGetVehicle(id: number) {
    return flatMap((user: User) => {
      return this.http.put((this.base + "reservation/client/" + user.id + "/getVehicle/" + id), {}, {
        headers: this.headers()
      })
    })(this.getUser());
  }

  confirmVehicleDeliver(id: number) {
    return flatMap((user: User) => {
      return this.http.put((this.base + "reservation/owner/" + user.id + "/confirmVehicleDelivery/" + id), {}, {
        headers: this.headers()
      })
    })(this.getUser());
  }

  clientReturnVehicle(id: number, clientScore: number) {
    return flatMap((user: User) => {
      return this.http.put((this.base + "reservation/client/" + user.id + "/returnVehicle/" + id), {score:clientScore}, {
        headers: this.headers()
      })
    })(this.getUser());
  }

  ownerFinishReserve(id: number, clientScore: number) {
    return flatMap((user: User) => {
      return this.http.put((this.base + "reservation/owner/" + user.id + "/reciveVehicle/" + id), {score:clientScore}, {
        headers: this.headers()
      })
    })(this.getUser());
  }
}
