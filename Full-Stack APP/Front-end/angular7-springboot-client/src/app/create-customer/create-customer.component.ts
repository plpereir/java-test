import { CustomerService } from './../customer.service';
import { Customer } from './../customer';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  customer: Customer = new Customer();
  tmessage: string;
  submitted = false;
 
  constructor(private customerService: CustomerService, 
		private route:ActivatedRoute, 
		private router:Router) { }

    ngOnInit() {}

  newCustomer(): void {
    this.customer = new Customer();
  }

  save() {
    this.customerService.createCustomer(this.customer)
      .subscribe(data => {console.log(data),this.gotoList()}, error => {console.log(error);this.tmessage = error.error.message; this.submitted = true;});
    this.customer = new Customer();
  }

     onSubmit() {
	this.submitted = false;
 	this.save();
    }

  gotoList() {
    this.router.navigate(['customer']);
  }
}
