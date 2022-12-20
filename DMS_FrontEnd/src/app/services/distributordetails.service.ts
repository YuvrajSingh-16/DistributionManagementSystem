import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class DistributordetailsService {

  constructor(private http:HttpClient) { }

  //add distriubutor
  public addDistributor(distributor:any)
  {
    return this.http.post(`${baseUrl}/admin/create-distributor`,distributor);
  }

  //get distributor
  public getDistributor()
  {
    return this.http.get(`${baseUrl}/admin/get-distributors`);
  }

  //disable distributor
  public disableDistributor(emailId:any){
    return this.http.get(`${baseUrl}/admin/disable-distributor/${emailId}`);
  }

  //enable distributor
  public enableDistributor(emailId:any){
    return this.http.get(`${baseUrl}/admin/enable-distributor/${emailId}`);
  }

  //get distributor of retailer
  public getDistributorOfRetailer(id:any){
    return this.http.get(`${baseUrl}/retailer/get-distributor-of-retailer/${id}`);
  }
}
