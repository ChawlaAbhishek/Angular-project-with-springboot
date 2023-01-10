import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthInterceptorInterceptor } from './auth-interceptor.interceptor';
import { LoginComponent } from './login/login.component';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private login:LoginComponent,private interceptor:AuthInterceptorInterceptor){}
  canActivate() {
       return this.login.isLoggedIn();
    }
  
}
