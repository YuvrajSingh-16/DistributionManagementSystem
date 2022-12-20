import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class DistOrderHistoryService {

  constructor(private _http: HttpClient) { }
  public orderHistory(pincode: any){
    return this._http.get(`${baseUrl}/distributor/order-history/${pincode}`)
  }
}
