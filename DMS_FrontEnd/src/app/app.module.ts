import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { NavbarComponent } from './component/navbar/navbar.component';
import { FooterComponent } from './component/footer/footer.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatOptionModule } from '@angular/material/core';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { authInterceptorProvider } from './services/auth.interceptor';
import {MatSelectModule} from '@angular/material/select';
import { DistributorDashboardComponent } from './pages/distributor/distributor-dashboard/distributor-dashboard.component';
import { RetailerDashboardComponent } from './pages/retailer/retailer-dashboard/retailer-dashboard.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import {MatListModule} from '@angular/material/list';
import { SidebarComponent } from './pages/admin/sidebar/sidebar.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { ViewCategoriesComponent } from './pages/admin/view-categories/view-categories.component';
import { ViewSubCategoriesComponent } from './pages/admin/view-sub-categories/view-sub-categories.component';
import {MatTableModule} from '@angular/material/table';
import { OrderComponent } from './pages/admin/order/order.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatGridListModule} from '@angular/material/grid-list';
import { AddDistributorComponent } from './pages/admin/add-distributor/add-distributor.component';
import { GetDistributorComponent } from './pages/admin/get-distributor/get-distributor.component';
import { UpdateProfileComponent } from './pages/update-profile/update-profile.component';
import { OrderhistoryComponent } from './pages/retailer/orderhistory/orderhistory.component';
import { RetailerSidebarComponent } from './pages/retailer/retailer-sidebar/retailer-sidebar.component';
import { ProductSubcategoryComponent } from './pages/retailer/product-subcategory/product-subcategory.component';
import { ViewProductsComponent } from './pages/retailer/view-products/view-products.component';
import { DistOrderHistoryComponent } from './pages/distributor/dist-order-history/dist-order-history.component';
import { DistributorSidebarComponent } from './pages/distributor/distributor-sidebar/distributor-sidebar.component';
import { GetRetailerComponent } from './pages/distributor/get-retailer/get-retailer.component';
import { ManageOrdersComponent } from './pages/distributor/manage-orders/manage-orders.component';
import { StockDetailsComponent } from './pages/distributor/stock-details/stock-details.component';
import { WelcomeDistComponent } from './pages/distributor/welcome-dist/welcome-dist.component';
import { WelcomeRetailerComponent } from './pages/retailer/welcome-retailer/welcome-retailer.component';
import { RetailerDetailsComponent } from './pages/admin/retailer-details/retailer-details.component';
import { MoreOrderDetailsComponent } from './pages/admin/order/more-order-details/more-order-details.component';
import { AddStockComponent } from './pages/distributor/stock-details/add-stock/add-stock.component';
import { MyDistributorComponent } from './pages/retailer/my-distributor/my-distributor.component';
import { RetailerStockComponent } from './pages/retailer/retailer-stock/retailer-stock.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    SignupComponent,
    LoginComponent,
    DistributorDashboardComponent,
    RetailerDashboardComponent,
    AdminDashboardComponent,
    ProfileComponent,
    SidebarComponent,
    WelcomeComponent,
    ViewCategoriesComponent,
    ViewSubCategoriesComponent,
    OrderComponent,
    AddDistributorComponent,
    GetDistributorComponent,
    UpdateProfileComponent,
    OrderhistoryComponent,
    RetailerSidebarComponent,
    ProductSubcategoryComponent,
    ViewProductsComponent,
    DistOrderHistoryComponent,
    DistributorSidebarComponent,
    GetRetailerComponent,
    ManageOrdersComponent,
    StockDetailsComponent,
    WelcomeDistComponent,
    WelcomeRetailerComponent,
    RetailerDetailsComponent,
    MoreOrderDetailsComponent,
    AddStockComponent,
    MyDistributorComponent,
    RetailerStockComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatOptionModule,
    HttpClientModule,
    MatSnackBarModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatSelectModule,
    MatListModule,
    MatTableModule,
    MatSlideToggleModule,
    MatGridListModule
  ],
  providers: [authInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }