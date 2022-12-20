import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient) { }

  //load all the category
  public categories() {
    return this.http.get(`${baseUrl}/products/`);
  }

  public getAllProducts(){
    return this.http.get(`${baseUrl}/products/`);
  }

  public setProduct(product:any)
  {
    localStorage.setItem('product', JSON.stringify(product));
  }

  //getUser : get user details
  public getProduct()
  {
    let productStr=localStorage.getItem("product");
    if(productStr!=null)
    {
      return JSON.parse(productStr)
    }
    
  }

  public setCategory(category:any)
  {
    localStorage.setItem('category', JSON.stringify(category));
  }

  //getUser : get user details
  public getCategory()
  {
    let categoryStr=localStorage.getItem("category");
    console.log(categoryStr)
    if(categoryStr!=null)
    {
      return JSON.parse(categoryStr);
    }
    
  }
}
