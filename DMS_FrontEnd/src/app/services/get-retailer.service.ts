import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class GetRetailerService {

  
  constructor(private _http:HttpClient) { }
  
//get-retailers
  public getRetailer(Id: any){
    return this._http.get(`${baseUrl}/distributor/${Id}/get-retailers`);
  }

  public retailerByAdmin(){
    return this._http.get(`${baseUrl}/admin/get-retailers`);
  }

  //find user by id
  public findUser(userId:any){
    return this._http.get(`${baseUrl}/distributor/find-user/${userId}`);
  }

  //disable retailer
  public disableRetailer(emailId:any){
    return this._http.get(`${baseUrl}/admin/disable-retailer/${emailId}`);
  }

  //enable retailer
  public enableRetailer(emailId:any){
    return this._http.get(`${baseUrl}/admin/enable-retailer/${emailId}`);
  }
}

