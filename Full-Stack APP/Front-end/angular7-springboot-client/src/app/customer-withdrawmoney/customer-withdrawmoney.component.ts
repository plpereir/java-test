import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-withdrawmoney',
  templateUrl: './customer-withdrawmoney.component.html',
  styleUrls: ['./customer-withdrawmoney.component.css']
})
export class CustomerWithdrawmoneyComponent implements OnInit {

  id: string;
  tcode : number;
  customer: Customer;
  tmessage: string;
  talertType: string;
  submitted = false;

  constructor(private route: ActivatedRoute,private router: Router,
    private customerService: CustomerService) { }

  ngOnInit() {
    this.customer = new Customer();

    this.id = this.route.snapshot.params['id'];
    
    this.customerService.getCustomer(this.id)
      .subscribe(data => {
        console.log(data)
        this.customer = data;
      }, error => console.log(error));
  }

  withdrawMoney(id, value) {
    this.customerService.withdrawmoneyCustomer(id, value)
      .subscribe(
        data => {
          console.log(data);
          this.tmessage = data[1].message;
	  this.talertType = data[0].alert;
    	  this.submitted = true;
	  
      },
        error => console.log(error));
  }

  onSubmit() {
    this.submitted = false;
    this.withdrawMoney(this.id, this.tcode);    
  }

  list(){
    this.router.navigate(['customer']);
  }

}
