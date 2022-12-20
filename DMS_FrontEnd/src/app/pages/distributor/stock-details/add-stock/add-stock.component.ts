import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { StockDetailsService } from 'src/app/services/stock-details.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.css']
})
export class AddStockComponent implements OnInit {

  stockId='';
  enteredQuantity='';

  newStock:any=
    {   
      id:'',
      availableQuantity:'',
      productCategories:{categoryId:'',products:{productId:''}},
      user:{userId:''}
  };

  stockDetails:any={
    availableQuantity:'',
    productCategories: {
        categoryId: '',
        categoryName: '',
        price:'',
        products: {
            productId: '',
            productName:'',
        }
    },
    id:'',
}

  constructor(private route:ActivatedRoute,private stock:StockDetailsService,private snack:MatSnackBar,private login:LoginService) { }

  ngOnInit(): void {
    this.stockId=this.route.snapshot.params['id'];
    this.newStock.user.userId= this.login.getUser().userId;

    this.stock.singleStockDetails(this.stockId).subscribe(
      (data:any)=>{
        this.stockDetails=data;
        console.log(this.stockDetails);
      },
      (error)=>{
        console.log(error);
      }
    )
  }

  public addStock(){
    if(this.enteredQuantity<this.stockDetails.availableQuantity)
    {
      this.snack.open("Please Enter Quantity More Than Available Quantity..!!", 'Ok' , { duration:3000})
    }
    else{

      this.newStock.id = this.stockDetails.id;
      this.newStock.availableQuantity = this.enteredQuantity;
      this.newStock.productCategories.categoryId = this.stockDetails.productCategories.categoryId;
      this.newStock.productCategories.products.productId = this.stockDetails.productCategories.products.productId;
      this.newStock.user.userId = this.login.getUser().userId;
      console.log(this.newStock)

      this.stock.updateStock(this.newStock).subscribe(
        (data)=>{
          Swal.fire('Done','Stock Updated Successfully','success').then((value) => {
            window.location.href='/distributor-dashboard/stock-details/'+ this.login.getUser().userId
          });
        },
        (error)=>{
          console.log(error);
        }
        )
    }
  }

}
