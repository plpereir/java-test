import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { Observable } from "rxjs";

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {
 
  id: string;
  customer: Customer;
  submitted = true;
  successful = false;

  constructor(private route: ActivatedRoute,private router: Router,
    private customerService: CustomerService) { }
    customers: Observable<Customer[]>;
  ngOnInit() {
    this.customer = new Customer();


    this.id = this.route.snapshot.params['id'];
    this.getCustomerID();

  }

  getCustomerID()
  {
    this.customerService.getCustomer(this.id)
      .subscribe(data => {
        console.log(data)
        this.customer = data;
      }, error => console.log(error));
  }

  reloadData() {
    this.customers = this.customerService.getCustomersList();
  }

  updateCustomer() {
    this.customerService.updateCustomer(this.id, this.customer)
      .subscribe(data => {console.log(data);this.submitted=data;this.successful=data;}, error => console.log(error));
    
    this.reloadData();
  }

  onSubmit() {
    this.updateCustomer();    
  }

  gotoList() {
    this.router.navigate(['customer']);
  }
}
