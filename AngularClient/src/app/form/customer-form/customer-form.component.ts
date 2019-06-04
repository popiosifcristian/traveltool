import {Customer} from './../../model/customer';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService} from './../../service/customer.service';

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent implements OnInit {
  model: Customer = new Customer();

  constructor(private route: ActivatedRoute, private router: Router, private service: CustomerService) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.model.id = 0;
    this.service.save(this.model).subscribe(() => this.goToModelList());
  }

  goToModelList() {
    this.router.navigate(['/customers']);
  }

}
