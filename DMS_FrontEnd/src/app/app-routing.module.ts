import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddDistributorComponent } from './pages/admin/add-distributor/add-distributor.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { GetDistributorComponent } from './pages/admin/get-distributor/get-distributor.component';
import { RetailerDetailsComponent } from './pages/admin/retailer-details/retailer-details.component';
import { UpdateProfileComponent } from './pages/update-profile/update-profile.component';
import { ViewCategoriesComponent } from './pages/admin/view-categories/view-categories.component';
import { ViewSubCategoriesComponent } from './pages/admin/view-sub-categories/view-sub-categories.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { DistOrderHistoryComponent } from './pages/distributor/dist-order-history/dist-order-history.component';
import { DistributorDashboardComponent } from './pages/distributor/distributor-dashboard/distributor-dashboard.component';
import { GetRetailerComponent } from './pages/distributor/get-retailer/get-retailer.component';
import { ManageOrdersComponent } from './pages/distributor/manage-orders/manage-orders.component';
import { StockDetailsComponent } from './pages/distributor/stock-details/stock-details.component';
import { WelcomeDistComponent } from './pages/distributor/welcome-dist/welcome-dist.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { OrderComponent } from './pages/admin/order/order.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { OrderhistoryComponent } from './pages/retailer/orderhistory/orderhistory.component';
import { ProductSubcategoryComponent } from './pages/retailer/product-subcategory/product-subcategory.component';
import { RetailerDashboardComponent } from './pages/retailer/retailer-dashboard/retailer-dashboard.component';
import { ViewProductsComponent } from './pages/retailer/view-products/view-products.component';
import { WelcomeRetailerComponent } from './pages/retailer/welcome-retailer/welcome-retailer.component';
import { SignupComponent } from './pages/signup/signup.component';
import { AdminGuard } from './services/admin.guard';
import { DistributorGuard } from './services/distributor.guard';
import { RetailerGuard } from './services/retailer.guard';
import { MoreOrderDetailsComponent } from './pages/admin/order/more-order-details/more-order-details.component';
import { AddStockComponent } from './pages/distributor/stock-details/add-stock/add-stock.component';
import { MyDistributorComponent } from './pages/retailer/my-distributor/my-distributor.component';
import { RetailerStockComponent } from './pages/retailer/retailer-stock/retailer-stock.component';

const routes: Routes = [

  //homepage
  {
    path:'',
    component:HomeComponent,
    pathMatch:'full',
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full',
  },
  {
    path:'signup',
    component:SignupComponent,
    pathMatch: 'full',
  },
  //admin
  {
    path:'admin-dashboard',
    component:AdminDashboardComponent,
    canActivate:[AdminGuard],
    children:[
      {
        path:'',
        component: WelcomeComponent,
      },
      {
        path:'profile',
        component: ProfileComponent,
      },
      {
        path:'update-profile',
        component:UpdateProfileComponent
      },
      {
        path:'categories',
        component: ViewCategoriesComponent,
      },
      {
        path:'subcategories/:productId',
        component: ViewSubCategoriesComponent,
      },
      {
        path:'add-distributor',
        component:AddDistributorComponent,
      },
      {
        path:'get-distributor',
        component:GetDistributorComponent,
      },
      {
        path:'get-retailer',
        component:RetailerDetailsComponent,
      },
      {
        path:'order',
        component:OrderComponent,
      },
      {
        path:'order/more-details/:userId',
        component:MoreOrderDetailsComponent
      }
    ]
  },
  
  //distributor
  {
    path:'distributor-dashboard',
    component:DistributorDashboardComponent,
    canActivate:[DistributorGuard],
    children:[
      {
        path:'',
        component: WelcomeDistComponent
      },
      {
        path:'profile',
        component:ProfileComponent,

      },
      {
        path:'update-profile',
        component:UpdateProfileComponent
      },
      {
        path:'manage-orders/:pincode',
        component: ManageOrdersComponent
      },
      {
        path:':Id/get-retailers',
        component: GetRetailerComponent
      },
      {
        path:'stock-details/:userID',
        component: StockDetailsComponent
      },
      {
        path:'add-more-stock/:id',
        component:AddStockComponent
      },
      {
        path:'dist-order-history/:pincode',
        component: DistOrderHistoryComponent
      }
      
    ]
  },

  //retailer
  {
    path:'retailer-dashboard',
    component:RetailerDashboardComponent,
    canActivate:[RetailerGuard],
    children:[
      {
        path:'',
        component: WelcomeRetailerComponent
      },
      {
        path:'profile',
        component:ProfileComponent,

      },
      {
        path:'update-profile',
        component:UpdateProfileComponent
      },
      {
        path:'my-distributor',
        component:MyDistributorComponent
      },
      {
        path:'view-products',
        component: ViewProductsComponent,
      },
      {
        path:'place-order',
        component:ProductSubcategoryComponent,
      },
      {
        path:'retailer-stock',
        component: RetailerStockComponent,
      },
      
    {
      path:'order-history',
      component:OrderhistoryComponent,
    },
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }