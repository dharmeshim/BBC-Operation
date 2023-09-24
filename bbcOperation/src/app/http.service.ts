import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  baseUrl:string=environment.baseUrl;

  constructor(private http:HttpClient) { }




  sendOtp(id:any){
    return (this.http.get('http://localhost:8080/employee/login/send-otp/'+id,{responseType:'text'}))
  }


  verifyOtp(obj:any){
    return (this.http.post("http://localhost:8080/employee/login/verify",obj));

  }

  addCustomer(customerObj: any):any {
    return this.http.post(`${this.baseUrl}api/v1/customer/add-new`, customerObj, { responseType: 'text' });
  }
  
  addBillToCustomer(billObj:any){
    return this.http.post(`${this.baseUrl}api/v1/bill/create`,billObj, { responseType: 'text' });
  }

  getAllTrasactions(){
    return this.http.get(`${this.baseUrl}trasaction/get-all`);
  }

  getAllCustomers(){
    return this.http.get(`${this.baseUrl}api/v1/customer/get-all`);
  }

  getUnpaidBills(customerId:number){
    return this.http.get(`${this.baseUrl}api/v1/bill/unpaid-bill/${customerId}`);
  }


  markBillAsPaid(bill:any){
    return this.http.put(`${this.baseUrl}api/v1/bill/mark-payment-cash`,bill, {responseType:'text'});
  }

  getTransactionsById(cusId:any){
    return this.http.get(`${this.baseUrl}trasaction/get-by-customer/${cusId}`);
  }



  uploadFile(file: File) {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.baseUrl}api/v1/bill/upload-bill-file`,formData);
  }

  uploadCustomerCsv(file: File) {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.baseUrl}api/v1/customer/upload-files`,formData);
  }


  getEmployeeById(id:any){
    return this.http.get(`${this.baseUrl}employee/get-employee/${id}`);
  }

  getBillDetailsById(billId:any){
    return this.http.get(`${this.baseUrl}api/v1/bill/get-bill/${billId}`);
  }


}
