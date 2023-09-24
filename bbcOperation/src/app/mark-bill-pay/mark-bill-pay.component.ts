import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-mark-bill-pay',
  templateUrl: './mark-bill-pay.component.html',
  styleUrls: ['./mark-bill-pay.component.css'],
})
export class MarkBillPayComponent implements OnInit {
  bills: any[] = [];
  p: number = 1;
  customerId!: any;

  constructor(
    private service: HttpService,
    private router: Router,
    private title: Title,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void { 
    this.title.setTitle("Bill Pay")
  }

  loadBills() {
    this.bills.splice(0);
    this.service.getUnpaidBills(this.customerId).subscribe((response: any) => {
      if (response.customerExists) {
        this.bills = response.unpaidBills;
      } else {
        this.toaster.error('Customer does not exist');
      }
    });
  }

  payBill(bill: any) {
    this.service.markBillAsPaid(bill).subscribe((response: any) => {
      if (response === 'true') {
        
        this.bills = this.bills.filter((b) => b !== bill);
        this.toaster.success('Bill Paid Successfully!!!');
      } else if (response === 'false') {
        this.toaster.error('Something went wrong!');
      }
    });
  }

  calculateAmountAfterDiscount(bill: any): number {
    const today = new Date();
    const dueDate = new Date(bill.billDueDate);

    if (today <= dueDate) {
      return bill.amountForEarlyPay;
    } else {
      return bill.billAmount;
    }
  }

  

 

}
