import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {VehicleService} from '../vehicles/vehicleService';
import {Vehicle} from '../vehicles/vehicle';


@Component({
  selector: 'app-create-vehicle',
  templateUrl: './create-vehicle.component.html',
  styleUrls: ['./create-vehicle.component.css'],
  providers: [VehicleService],
})
export class CreateVehicleComponent implements OnInit {
  @Input() userId: number;
  dialogVehicle = false;
  pictures: string[];
  vehicleForm: FormGroup;
  vehicle: Vehicle;


  constructor (private vehicleService: VehicleService) { }

  ngOnInit() {
    this.vehicleForm = new FormGroup({
      passengers: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      license: new FormControl('', Validators.required)
    });
    this.pictures = [];
  }

  toggleAddVehicleDialog() {
    this.dialogVehicle = !this.dialogVehicle;
  }

  addPicture(picture: string) {
    if (this.pictures.length < 6) {
      this.pictures.push(picture);
    }
  }

  onSubmit() {
    this.vehicle = this.vehicleForm.value;
    this.vehicleService.createAndAddVehicleToUser(this.userId, this.vehicle);
  }


}
