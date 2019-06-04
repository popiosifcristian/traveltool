import { ActivatedRoute, Router } from '@angular/router';
import { Trip } from './../../model/trip';
import { Component, OnInit } from '@angular/core';
import { TripService } from 'src/app/service/trip.service';

@Component({
  selector: 'app-trip-form',
  templateUrl: './trip-form.component.html',
  styleUrls: ['./trip-form.component.css']
})
export class TripFormComponent implements OnInit {
  model: Trip;
  constructor(private route: ActivatedRoute, private router: Router, private service: TripService) {
    this.model = new Trip();
   }

   ngOnInit() {}

  onSubmit() {
    this.service.save(this.model).subscribe(result => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/trips']);
  }

}
