import {ActivatedRoute, Router} from '@angular/router';
import {Trip} from './../../model/trip';
import {Component, OnInit} from '@angular/core';
import {TripService} from 'src/app/service/trip.service';
import {LandmarkService} from '../../service/landmark.service';
import {CompanyService} from '../../service/company.service';

@Component({
  selector: 'app-trip-form',
  templateUrl: './trip-form.component.html',
  styleUrls: ['./trip-form.component.css']
})
export class TripFormComponent implements OnInit {
  model: Trip;
  landmark: bigint;
  transportCompany: bigint;

  constructor(private route: ActivatedRoute, private router: Router, private service: TripService, private landmarkService: LandmarkService,
              private companyService: CompanyService) {
    this.model = new Trip();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.model.id = 0;
    this.companyService.getOne(this.transportCompany).subscribe(data => this.model.transportCompany = data);
    this.landmarkService.getOne(this.landmark).subscribe(data => this.model.landmark = data);
    this.service.save(this.model).subscribe(() => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/trips']);
  }

}
