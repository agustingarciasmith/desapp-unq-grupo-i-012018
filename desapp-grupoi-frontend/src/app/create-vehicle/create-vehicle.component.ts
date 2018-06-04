import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-vehicle',
  templateUrl: './create-vehicle.component.html',
  styleUrls: ['./create-vehicle.component.css']
})
export class CreateVehicleComponent implements OnInit {

  dialogVehicle: boolean = false;

  toggleAddVehicleDialog() {
        this.dialogVehicle = !this.dialogVehicle;
    }

  constructor() { }

  ngOnInit() {
  }

}
