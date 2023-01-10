import { Injectable,OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from './user';
import { LoginComponent } from './login.component';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {

  


  constructor(private httpClient:HttpClient) { }

   private baseURL = "http://localhost:8088/basicAuth"

   

   ngOnInit(): void {
    
  }

  requestHeader=new HttpHeaders(
    {"No-Auth":"True"}
  )


  authenticationService(){


    // return this.httpClient.get(`${this.baseURL}`,{responseType:'text', headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res)=>{
    //   this.username=username;
    //   this.password=password;
    // }))

   

    return this.httpClient.get(`${this.baseURL}`,{responseType:'text' as 'json'})

    

  } 

  authenticateService(user:any){

    return this.httpClient.post("http://localhost:8088/login",user,{headers:this.requestHeader});

  }
  
  

  

  //   createBasicAuthToken(username: string, password: string) {
  //   return 'Basic ' + window.btoa(username + ":" + password);
  // }



}
