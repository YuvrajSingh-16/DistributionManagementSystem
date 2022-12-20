import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import { LoginService } from 'src/app/services/login.service';
import { OrderService } from 'src/app/services/order.service';
import { SubcategoryService } from 'src/app/services/subcategory.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-subcategory',
  templateUrl: './product-subcategory.component.html',
  styleUrls: ['./product-subcategory.component.css']
})
export class ProductSubcategoryComponent implements OnInit {

  categories=[{
    categoryId:'',
    categoryName:'',
    productId:'',
    price:''
  }]
  category={
      categoryId:'',
      products: {
        productId: '',
      },
      price:''
  }

  constructor(private _simsubcat:SubcategoryService,public _categoryService:CategoryService,public _order:OrderService,public _login:LoginService) { }
   productId:any;
   public quantity:number=0;
  ngOnInit(): void { 
    
    // this.user = this.login.getUser();
    // this.productId=this.route.snapshot.params['productId'];
    console.log("printing id");
    this.productId=this._categoryService.getProduct().productId;
    console.log(this._categoryService.getProduct().productId);
    this._simsubcat.showSubcategories(this.productId).subscribe(
      (data:any)=>{
        this.categories=data;
        this._categoryService.setCategory(this.categories);
      },

      (error:any)=>{
        console.log(error);
        Swal.fire("ERROR IN LOADING DATA");
      }
    );
  }

  addQuantity(){
    this.quantity=this.quantity+50;
    this._order.setQuantity(this.quantity);
  }

  minusQuantity(){
    if(this.quantity-50<0){
      this.quantity=0;
      Swal.fire('Error!! ', 'Quantity can not be less than zero', 'error');
    }else{
    this.quantity=this.quantity-50;
    this._order.setQuantity(this.quantity);
    console.log(this._order.getQuantity());
  }
  }

  categorySelect(c:any){
    console.log("Inside Select Category");
    this.category = c;
    this._categoryService.setCategory(c);
    console.log(this.category);
  }
  
  place_Order() {

    
    const order= {
      userId: this._login.getUser().userId,
      quantity: this._order.getQuantity(),
      totalAmount: this._order.getQuantity()* parseInt(this.categories[0].price),
      categoryId: this._categoryService.getCategory().categoryId,

      orderStatus:"UNDER_PROCESS",
      user: {
        userId: this._login.getUser().userId
      },
      productCategories:{
        categoryId: this._categoryService.getCategory().categoryId,
        products: {
            productId: this._categoryService.getCategory().products.productId
        }
    },
  
    };
    console.log(order);
    this._order.placeOrder(order).subscribe(
      (data) => {
        console.log("data is present");
        Swal.fire('Success', 'Order Placed', 'success');
        
        },

      (error) => {
        console.log("error");
        Swal.fire('Sorry!! ', 'Either Distributor or Stock not available', 'error');
        console.log(error);
      }
    );
  }

}