import {Employee} from '../../model/employee';
import {EmployeeService} from '../../service/employee.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CompanyService} from '../../service/company.service';
import {Company} from '../../model/company';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {
  model: Employee;
  agencyId: bigint;
  agency: Company;

  constructor(private route: ActivatedRoute, private router: Router, private service: EmployeeService,
              private companyService: CompanyService) {
    this.model = new Employee();
  }

  ngOnInit() {
  }

  async onSubmit() {
    this.model.id = 0;
    this.agency = await this.companyService.getOne(this.agencyId).toPromise();
    this.model.agency = this.agency;
    this.service.save(this.model).subscribe(() => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/employees']);
  }
}
