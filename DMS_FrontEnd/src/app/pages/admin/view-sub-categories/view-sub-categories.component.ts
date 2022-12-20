import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { subscriptionLogsToBeFn } from 'rxjs/internal/testing/TestScheduler';
import { SubcategoryService } from 'src/app/services/subcategory.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-sub-categories',
  templateUrl: './view-sub-categories.component.html',
  styleUrls: ['./view-sub-categories.component.css']
})
export class ViewSubCategoriesComponent implements OnInit {

  subCategoryDetail=[
    {
      categoryName:'',
      price:'',
      categoryId:'',
    }
  ]

  constructor(private _route:ActivatedRoute, private subCategory:SubcategoryService) { }
  
  productId:any;

  ngOnInit(): void {
    this.productId=this._route.snapshot.params['productId'];
    this.subCategory.showSubcategories(this.productId).subscribe(
      (data:any)=>{
        this.subCategoryDetail=data;
        console.log(data);
      },
      (error)=>{
        Swal.fire('Error','Unable to load data from server','error');
      }
    )
  }

  displayedColumns: string[] = ['categoryId', 'categoryName', 'price'];
  dataSource = this.subCategoryDetail;

}
