import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  EMAIL_PATTERN: string = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  PHONENUMBER_PATTERN: RegExp = /^\d{10}$/;

  NAME_PATTERN: string = "^[A-Za-z\\s'-]+$";


  constructor(private service: HttpService,
    private router: Router,
    private title: Title,
    private toaster: ToastrService) { }

  ngOnInit(): void {
    this.title.setTitle("Add Customer");
  }

  onSubmit(f: NgForm) {

    let customerObj = {
      name: f.value.name,
      email: f.value.emailId,
      telephone: f.value.phoneno,
      address: f.value.address
    }


    this.service.addCustomer(customerObj)
      .subscribe((response: any) => {
        if (response === 'true') {
          this.toaster.success('Added Successfully!!!');
        } else if (response === 'false') {
          this.toaster.error('Customer already exists with this Mobile number!!!');
        }
      });

  }
}