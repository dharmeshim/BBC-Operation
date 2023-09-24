import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private title:Title,
    private service:HttpService,
    private toaster:ToastrService
  ) { }

  ngOnInit(): void {

    this.title.setTitle("BBC Ops")
  }

}
