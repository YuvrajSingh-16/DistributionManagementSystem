
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) {
   }

  //adduser
  public adduser(user:any){
      return this.http.post(`${baseUrl}/retailer/`,user);
  }

  //add distributor
  public addDistributor(user:any){
    return this.http.post(`${baseUrl}/admin/create-distributor`,user);
  }

}