import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ViewCategoriesComponent } from '../pages/admin/view-categories/view-categories.component';
import { CategoryService } from './category.service';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class SubcategoryService {

  constructor(private http:HttpClient) { }

  //load all product of category

  public showSubcategories(productId:any)
  {
    return this.http.get(`${baseUrl}/category/product/${productId}`);
  }
}
