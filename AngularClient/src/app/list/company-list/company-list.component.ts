import { Component, OnInit } from '@angular/core';
import {Company} from '../../model/company';
import {CompanyService} from '../../service/company.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {

  companies: Company[];

  constructor(private companyService: CompanyService, private router: Router) {
  }

  ngOnInit() {
    this.companyService.findAll().subscribe(data => {
      this.companies = data;
    });
  }

  deleteModel(model: Company) {
    this.companyService.delete(model).subscribe(() => {
      this.companyService.findAll().subscribe(data => {
        this.companies = data;
      });
    });
  }
}
