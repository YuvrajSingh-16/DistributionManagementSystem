import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { SubcategoryService } from 'src/app/services/subcategory.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {

  categories=[
    {
      productId:'',
      productName:'',
    },
  ]

  
  constructor(private _category: CategoryService) { }

  ngOnInit(): void {

    this._category.categories().subscribe(
      (data:any)=>{
        // css
        this.categories=data;
        console.log(this.categories);
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error..!','Something went wrong', 'error')
      }
    )
  }
}