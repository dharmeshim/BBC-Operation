import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';
import { NgForm, AbstractControl, ValidationErrors } from '@angular/forms';


@Component({
  selector: 'app-add-bill',
  templateUrl: './add-bill.component.html',
  styleUrls: ['./add-bill.component.css']
})
export class AddBillComponent implements OnInit {

  durationOfBill!: Date;
  billDueDate!: Date;

  constructor(private service: HttpService,
    private router: Router,
    private title: Title,
    private toaster: ToastrService) {
  }

  ngOnInit(): void {
    this.title.setTitle("Add Bill")
  }


  onSubmit(f: NgForm) {
    const durationOfBill: Date = this.durationOfBill;
    const billDueDate: Date = this.billDueDate;


    if (durationOfBill && billDueDate && durationOfBill <= billDueDate) {
      let billObj = {
        unitConsumption: f.value.unitscon,
        durationOfBill: durationOfBill,
        billDueDate: billDueDate,
        customer: {
          customerId: f.value.cusId
        }
      };


      this.service.addBillToCustomer(billObj).subscribe((response: any) => {
        if (response === 'true') {
          this.toaster.success('Added Successfully!!!');
        } else if (response === 'false') {
          this.toaster.error('No customer found with this ID!!!');
        }
      });
    } else {
      this.toaster.error('Due Date must be greater than Duration of Bill');
    }
  }

}
