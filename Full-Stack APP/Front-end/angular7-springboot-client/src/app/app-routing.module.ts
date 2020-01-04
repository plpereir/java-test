import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { CustomerWithdrawmoneyComponent } from './customer-withdrawmoney/customer-withdrawmoney.component';

const routes: Routes = [
  { path: '', redirectTo: 'customer', pathMatch: 'full' },
  { path: 'customer', component: CustomerListComponent },
  { path: 'add', component: CreateCustomerComponent },
  { path: 'details/:id', component: CustomerDetailsComponent },
  { path: 'update/:id', component: UpdateCustomerComponent },
  { path: 'withdrawmoney/:id', component: CustomerWithdrawmoneyComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
