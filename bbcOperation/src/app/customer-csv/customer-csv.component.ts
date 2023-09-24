import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-customer-csv',
  templateUrl: './customer-csv.component.html',
  styleUrls: ['./customer-csv.component.css']
})
export class CustomerCsvComponent implements OnInit {

  customers: any[] = [];
  selectedFile: File | undefined;

  constructor(
    private service: HttpService,
    private router: Router,
    private title: Title,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void {
  }


  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {
    this.customers.splice(0);
    if (this.selectedFile) {
      this.toaster.success("File Uploading!")
      this.service.uploadCustomerCsv(this.selectedFile).subscribe((response: any) => {
        this.customers = response;
      });
    }
  }

}
