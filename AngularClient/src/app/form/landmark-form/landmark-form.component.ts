import { ActivatedRoute, Router } from '@angular/router';
import { Landmark } from './../../model/landmark';
import { Company } from 'src/app/model/company';
import { Component, OnInit } from '@angular/core';
import { LandmarkService } from 'src/app/service/landmark.service';

@Component({
  selector: 'app-landmark-form',
  templateUrl: './landmark-form.component.html',
  styleUrls: ['./landmark-form.component.css']
})
export class LandmarkFormComponent implements OnInit {
  model: Landmark;
  constructor(private route: ActivatedRoute, private router: Router, private service: LandmarkService) {
    this.model = new Landmark();
   }

   ngOnInit() {}

  onSubmit() {
    this.service.save(this.model).subscribe(result => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/landmarks']);
  }

}
