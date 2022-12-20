import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  //current user:which is logged in
  public getCurrentUser()
  {
    return this.http.get(`${baseUrl}/current-user`);
  }

  //generate token

  public generateToken(loginData: any){
    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }

  //Login user: set token in LocalStorage
  public loginUser(token: any)
  {
    localStorage.setItem('token',token);
    return true;
  }

  //isLogin: user is logged in or not
  public isLoggedIn()
  {
    let tokenStr=localStorage.getItem("token");
    if (tokenStr==undefined || tokenStr=='' || tokenStr==null)
    {
      return false;
    }else{
      return true;
    }
  }

  //logout : remove token from local storage
  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //getToken
  public getToken()
  {
    return localStorage.getItem('token');
  }

  //set userDetail
  public setUser(user :any)
  {
    localStorage.setItem('user', JSON.stringify(user));
  }

  //getUser : get user details
  public getUser()
  {
    let userStr=localStorage.getItem("user");
    if(userStr!=null)
    {
      return JSON.parse(userStr)
    }else{
      this.logout();
      return null;
    }
  }

  //get user role
  public getUserRole()
  {
    let user=this.getUser()
    return user.authorities[0].authority;
  }

  //get admin profile details
  public getAdminProfile(email:any){
    return this.http.get(`${baseUrl}/admin/${email}`);
  }

  //update admin profile details
  public updateAdminProfile(dataOfProfile:any){
    return this.http.put(`${baseUrl}/admin/`,dataOfProfile);
  }

  //get admin profile details
  public getDistributorProfile(email:any){
    return this.http.get(`${baseUrl}/distributor/${email}`);
  }

  //update distributor profile details
  public updateDistributorProfile(dataOfProfile:any){
    return this.http.put(`${baseUrl}/distributor/`,dataOfProfile);
  }

  //get retailer profile details
  public getRetailerProfile(email:any){
    return this.http.get(`${baseUrl}/retailer/${email}`);
  }

  //update retailer profile details
  public updateRetailerProfile(dataOfProfile:any){
    return this.http.put(`${baseUrl}/retailer/`,dataOfProfile);
  }

  //find user by id
  public findUser(userId:any){
    return this.http.get(`${baseUrl}/admin/find-user/${userId}`);
  }

  //get distributor of retailer
  public getDistributorOfRetailer(userId:any){
    return this.http.get(`${baseUrl}/admin/get-distributor-of-retailer/${userId}`);
  }
}
