import { CompanyService } from './../../service/company.service';
import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/model/company';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.css']
})
export class CompanyFormComponent implements OnInit {
  model: Company;
  constructor(private route: ActivatedRoute, private router: Router, private service: CompanyService) {
    this.model = new Company();
   }

   ngOnInit() {}

  onSubmit() {
    this.service.save(this.model).subscribe(result => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/companies']);
  }
}
