import {Component, OnInit} from '@angular/core';
import {BackendService} from '../backend/backend.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private backend: BackendService) {
    this.backend = backend;
  }

  ngOnInit() {
  }

  holis() {
    this.backend.test()
      .subscribe(
        (val) => {
          console.log(val);
          alert(val);
        },
        (cause) => {
          console.log(cause);
          alert(cause.message);
        });
  }
}
