import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-customer-transaction',
  templateUrl: './customer-transaction.component.html',
  styleUrls: ['./customer-transaction.component.css']
})
export class CustomerTransactionComponent implements OnInit {


  transactions: any[] = [];
  p: number = 1;
  customerId!: any;

  constructor(
    private service: HttpService,
    private router: Router,
    private title: Title,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void {
    this.title.setTitle("Transactions")
  }

  loadTransactions() {
    this.transactions.splice(0);
    this.service.getTransactionsById(this.customerId)
      .subscribe((response: any) => {
        this.transactions = response;
      },
        (error) => {
          this.toaster.error("Customer Not Exist")
        });

  }

}
