import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-trasactions',
  templateUrl: './list-trasactions.component.html',
  styleUrls: ['./list-trasactions.component.css']
})
export class ListTrasactionsComponent implements OnInit {

  transactions: any[] = [];
  p: number = 1;
  BillID: string = '';

  constructor(private title: Title,
    private service: HttpService,
    private toaster: ToastrService

  ) { }

  ngOnInit(): void {
    this.getAllTrasactions();
    this.title.setTitle("All Trasactions");
  }


  getAllTrasactions() {
    this.service.getAllTrasactions()
      .subscribe((response: any) => {
        this.transactions = response;
      })
  }

}
