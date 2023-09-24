import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpService } from './http.service';
import { LoginOtpComponent } from './login-otp/login-otp.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { HeaderComponent } from './header/header.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AllCustomersComponent } from './all-customers/all-customers.component';
import { CustomerCsvComponent } from './customer-csv/customer-csv.component';
import { AddBillComponent } from './add-bill/add-bill.component';
import { BillCsvComponent } from './bill-csv/bill-csv.component';
import { MarkBillPayComponent } from './mark-bill-pay/mark-bill-pay.component';
import { ListTrasactionsComponent } from './list-trasactions/list-trasactions.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgxPaginationModule } from 'ngx-pagination';
import { CustomerTransactionComponent } from './customer-transaction/customer-transaction.component';
import { LogOutComponent } from './log-out/log-out.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginOtpComponent,
    HomeComponent,
    HeaderComponent,
    AddCustomerComponent,
    AllCustomersComponent,
    CustomerCsvComponent,
    AddBillComponent,
    BillCsvComponent,
    MarkBillPayComponent,
    ListTrasactionsComponent,
    EmployeeDetailsComponent,
    CustomerTransactionComponent,
    LogOutComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatSidenavModule,
    MatInputModule,
    MatDatepickerModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 5000,
      positionClass: 'toast-top-right',
      preventDuplicates: true
    })
  ],
  providers: [HttpService],
  bootstrap: [AppComponent],
})
export class AppModule { }
