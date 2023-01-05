import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './login/user';
import { LoginComponent } from './login/login.component';

@Injectable()
export class AuthInterceptorInterceptor implements HttpInterceptor {

  // username:any;
  // password:any;
 // user:User=new User();

  constructor() {}
  createBasicAuthToken() {
   // console.log(this.login.user.username,"login")
   let username=localStorage.getItem("username")
   let password =localStorage.getItem("password")
   //console.log(username,password)
    return 'Basic ' + window.btoa(username + ":" + password);
  }

  // sendToInterceptor(username:string,password:string){
  //   this.username=username;
  //   this.password=password;

  // }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {


    let reqUrl:String = request.url;

    if(!request.headers.has('authorization')){
      request=request.clone({headers:request.headers.set('authorization',this.createBasicAuthToken())});

    }

    //  console.log(request,"request")
    
    return next.handle(request);
  }
}
