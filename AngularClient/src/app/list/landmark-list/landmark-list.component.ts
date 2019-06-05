import { Component, OnInit } from '@angular/core';
import {LandmarkService} from '../../service/landmark.service';
import {Landmark} from '../../model/landmark';
import {Router} from '@angular/router';

@Component({
  selector: 'app-landmark-list',
  templateUrl: './landmark-list.component.html',
  styleUrls: ['./landmark-list.component.css']
})
export class LandmarkListComponent implements OnInit {

  landmarks: Landmark[];

  constructor(private landmarkService: LandmarkService, private router: Router) {
  }

  ngOnInit() {
    this.landmarkService.findAll().subscribe(data => {
      this.landmarks = data;
    });
  }

  deleteModel(model: Landmark) {
    this.landmarkService.delete(model).subscribe(() => {
      this.landmarkService.findAll().subscribe(data => {
        this.landmarks = data;
      });
    });
  }
}
