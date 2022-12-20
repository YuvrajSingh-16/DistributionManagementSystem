import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class StockDetailsService {

  constructor(private _http: HttpClient) { }
  
  public stockDetails(userID: any)
  {
    return this._http.get(`${baseUrl}/available-stock/${userID}`);
  }

  public singleStockDetails(stockId:any){
    return this._http.get(`${baseUrl}/available-stock/get-stock/${stockId}`);
  }

  public updateStock(details:any){
    return this._http.put(`${baseUrl}/available-stock/update-stock`,details);
  }
}
