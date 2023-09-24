import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginOtpComponent } from './login-otp/login-otp.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth.guard';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { AllCustomersComponent } from './all-customers/all-customers.component';
import { CustomerCsvComponent } from './customer-csv/customer-csv.component';
import { AddBillComponent } from './add-bill/add-bill.component';
import { BillCsvComponent } from './bill-csv/bill-csv.component';
import { MarkBillPayComponent } from './mark-bill-pay/mark-bill-pay.component';
import { ListTrasactionsComponent } from './list-trasactions/list-trasactions.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { CustomerTransactionComponent } from './customer-transaction/customer-transaction.component';
import { LogOutComponent } from './log-out/log-out.component';


const routes: Routes = [

  {path:"", redirectTo:"/login",pathMatch:"full"},
  {path:"login", component:LoginOtpComponent},
  {path:"home", component:HomeComponent, canActivate:[AuthGuard]},
  {path:"add-customer",component:AddCustomerComponent, canActivate:[AuthGuard]},
  {path:"all-customers", component:AllCustomersComponent, canActivate:[AuthGuard]},
  {path:"customer-csv-upload", component:CustomerCsvComponent, canActivate:[AuthGuard]},
  {path:"add-bill", component:AddBillComponent, canActivate:[AuthGuard]},
  {path:"bill-csv-upload",component:BillCsvComponent, canActivate:[AuthGuard]},
  {path:"mark-bill-pay",component:MarkBillPayComponent, canActivate:[AuthGuard]},
  {path:"list-all-trasactions", component:ListTrasactionsComponent, canActivate:[AuthGuard]},
  {path:"profile-details", component:EmployeeDetailsComponent, canActivate:[AuthGuard]},
  {path:"customer-transactions", component:CustomerTransactionComponent, canActivate:[AuthGuard]},
  {path:"log-out", component:LogOutComponent, canActivate:[AuthGuard]},

  {path:"**", component:NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
