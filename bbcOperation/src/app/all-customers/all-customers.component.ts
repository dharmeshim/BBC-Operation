import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-all-customers',
  templateUrl: './all-customers.component.html',
  styleUrls: ['./all-customers.component.css']
})
export class AllCustomersComponent implements OnInit {



  customers: any[] = [];
  p: number = 1;

  constructor(
    private title: Title,
    private service: HttpService,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void {
    this.getAllCustomers();
    this.title.setTitle("Customers");
  }


  getAllCustomers() {

    this.service.getAllCustomers()
      .subscribe((response: any) => {
        this.customers = response;
      })
  }

}
