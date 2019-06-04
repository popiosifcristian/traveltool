import { Employee } from './../../model/employee';
import { EmployeeService } from './../../service/employee.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {
  model: Employee;
  constructor(private route: ActivatedRoute, private router: Router, private service: EmployeeService) {
    this.model = new Employee();
   }

   ngOnInit() {}

  onSubmit() {
    this.service.save(this.model).subscribe(result => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/employees']);
  }
}
