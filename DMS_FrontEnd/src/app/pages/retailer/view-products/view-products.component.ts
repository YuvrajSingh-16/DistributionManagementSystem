import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { SubcategoryService } from 'src/app/services/subcategory.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css']
})
export class ViewProductsComponent implements OnInit {

  products= [{
    productId: '',
    productName:''
  }];

  constructor(public simsubcat:SubcategoryService, public _product:CategoryService) { }

  ngOnInit(): void {
    this._product.getAllProducts().subscribe(
      (data: any) => {
        //css
        this.products = data;
        console.log(this.products);
      },

      (error) => {
        //
        console.log(error);
        Swal.fire('Error !!', 'Error in loading products data', 'error');
      }
    );
  }

}
