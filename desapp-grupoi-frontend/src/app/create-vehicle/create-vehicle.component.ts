import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {VehicleService} from '../vehicles/vehicleService';
import {Vehicle} from '../vehicles/vehicle';
import {BackendService} from "../backend/backend.service";


@Component({
  selector: 'app-create-vehicle',
  templateUrl: './create-vehicle.component.html',
  styleUrls: ['./create-vehicle.component.css'],
  providers: [VehicleService]
})
export class CreateVehicleComponent implements OnInit {
  @Input() userId: number;
  dialogVehicle = false;
  pictures: string[];
  newVehicle: Vehicle;

  constructor (private service: BackendService) {
  }

  ngOnInit() {
    this.newVehicle = Vehicle.emptyVehicle();
  }

  toggleAddVehicleDialog() {
    this.dialogVehicle = !this.dialogVehicle;
  }

  addPicture(picture: string) {
    if (this.pictures.length < 6) {
      this.pictures.push(picture);
    }
  }

  addVehicleToUser() {
    this.service.addVehicleToUser(this.newVehicle);
    this.toggleAddVehicleDialog();
  }

  addVehicle() {
    this.newVehicle = Vehicle.emptyVehicle() ;
    this.toggleAddVehicleDialog();
  }
}
