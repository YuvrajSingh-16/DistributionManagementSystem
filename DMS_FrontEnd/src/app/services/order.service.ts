import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http:HttpClient) { }

  //load all the orders
  public order(){
    return this.http.get(`${baseUrl}/admin/get-all-orders`);
  }

  public getOrderHistory(userId:any){
    return this.http.get(`${baseUrl}/order/retailer/${userId}`);
  }

  public placeOrder(order:any){
    console.log("inside place order");
    console.log(order.userId);
    console.log(order.quantity);
    console.log(order.totalAmount);
    console.log(order.categoryId);
    console.log(order.user.userId);
    console.log(order.productCategories.categoryId);
    console.log(order.orderStatus);
    return this.http.post(`${baseUrl}/retailer/place-order`,order);
  }

  public setQuantity(quantity:number)
  {
    localStorage.setItem('quantity', JSON.stringify(quantity));
  }

  //getUser : get user details
  public getQuantity()
  {
    let quantityStr=localStorage.getItem("quantity");
    if(quantityStr!=null)
    {
      return JSON.parse(quantityStr);
    }
    
  }

  public viewOrders(pincode: any){
    return this.http.get(`${baseUrl}/distributor/order-requests/${pincode}`)
  }

  public approveOrder(orderId:any,categoryId:any){
    return this.http.get(`${baseUrl}/distributor/deliver-order/${orderId}/${categoryId}`);
  }
}
