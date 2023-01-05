import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private httpClient:HttpClient) { }

   private baseURL = "http://localhost:8088/basicAuth"


  authenticationService(){


    // return this.httpClient.get(`${this.baseURL}`,{responseType:'text', headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res)=>{
    //   this.username=username;
    //   this.password=password;
    // }))

   

    return this.httpClient.get(`${this.baseURL}`,{responseType:'text' as 'json'})

    

  } 
  
  

  

  //   createBasicAuthToken(username: string, password: string) {
  //   return 'Basic ' + window.btoa(username + ":" + password);
  // }



}
