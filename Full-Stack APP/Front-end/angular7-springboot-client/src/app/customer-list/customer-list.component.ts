import { CustomerDetailsComponent } from '../customer-details/customer-details.component';
import { UpdateCustomerComponent } from '../update-customer/update-customer.component';
import { CustomerWithdrawmoneyComponent } from '../customer-withdrawmoney/customer-withdrawmoney.component';
import { Observable } from "rxjs";
import { CustomerService } from "./../customer.service";
import { Customer } from "./../customer";
import { Component, OnInit } from "@angular/core";
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: "app-customer-list",
  templateUrl: "./customer-list.component.html",
  styleUrls: ["./customer-list.component.css"]
})
export class CustomerListComponent implements OnInit {
  customers: Observable<Customer[]>;

  constructor(private customerService: CustomerService, private route:ActivatedRoute,private router:Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.customers = this.customerService.getCustomersList();
  }

  deleteCustomer(id: string) {
    this.customerService.deleteCustomer(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  customerDetails(id: string){
    this.router.navigate(['details', id]);
  }

  updateCustomer(id: string){
    this.router.navigate(['update', id]);
  }

  customerWithdrawMoney(id: string){
    this.router.navigate(['withdrawmoney', id]);
  }

}

