import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-bill-csv',
  templateUrl: './bill-csv.component.html',
  styleUrls: ['./bill-csv.component.css']
})
export class BillCsvComponent implements OnInit {

  savedBills: any[] = [];
  selectedFile: File | undefined;

  constructor(
    private service: HttpService,
    private router: Router,
    private title: Title,
    private toaster: ToastrService
  ) { }

  ngOnInit(): void {
    this.title.setTitle("Bill Csv")
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {

    if (this.selectedFile) {
      this.toaster.success("File Uploading!")
      this.service.uploadFile(this.selectedFile).subscribe((response: any) => {
        this.savedBills = response;
      });
    }
  }

}
