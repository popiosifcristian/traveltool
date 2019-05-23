import { Component, OnInit } from '@angular/core';
import {LandmarkService} from '../../service/landmark.service';
import {Landmark} from '../../model/landmark';

@Component({
  selector: 'app-landmark-list',
  templateUrl: './landmark-list.component.html',
  styleUrls: ['./landmark-list.component.css']
})
export class LandmarkListComponent implements OnInit {

  landmarks: Landmark[];

  constructor(private landmarkService: LandmarkService) {
  }

  ngOnInit() {
    this.landmarkService.findAll().subscribe(data => {
      this.landmarks = data;
    });
  }
}
