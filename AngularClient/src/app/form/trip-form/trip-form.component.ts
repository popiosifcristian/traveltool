import {ActivatedRoute, Router} from '@angular/router';
import {Trip} from './../../model/trip';
import {Component, OnInit} from '@angular/core';
import {TripService} from 'src/app/service/trip.service';
import {LandmarkService} from '../../service/landmark.service';
import {CompanyService} from '../../service/company.service';
import {Landmark} from '../../model/landmark';
import {Company} from '../../model/company';

@Component({
  selector: 'app-trip-form',
  templateUrl: './trip-form.component.html',
  styleUrls: ['./trip-form.component.css']
})
export class TripFormComponent implements OnInit {
  model: Trip;
  landmarkId: bigint;
  transportCompanyId: bigint;
  landmark: Landmark;
  transportCompany: Company;

  constructor(private route: ActivatedRoute, private router: Router, private service: TripService, private landmarkService: LandmarkService,
              private companyService: CompanyService) {
    this.model = new Trip();
  }

  ngOnInit() {
  }

  async onSubmit() {
    this.model.id = 0;
    this.transportCompany = await this.companyService.getOne(this.transportCompanyId).toPromise();
    this.landmark = await this.landmarkService.getOne(this.landmarkId).toPromise();
    this.model.landmark = this.landmark;
    this.model.transportCompany = this.transportCompany;
    this.service.save(this.model).subscribe(() => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/trips']);
  }

}
