import {Employee} from '../../model/employee';
import {EmployeeService} from '../../service/employee.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CompanyService} from '../../service/company.service';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {
  model: Employee;
  agency: bigint;

  constructor(private route: ActivatedRoute, private router: Router, private service: EmployeeService,
              private companyService: CompanyService) {
    this.model = new Employee();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.model.id = 0;
    this.companyService.getOne(this.agency).subscribe(data => this.model.agency = data);
    console.log(this.model);
    this.service.save(this.model).subscribe(() => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/employees']);
  }
}
