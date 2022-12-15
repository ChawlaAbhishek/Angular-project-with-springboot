import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Address } from './address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private httpClient:HttpClient) { }

  private baseURL = "http://localhost:8088/Addresses";

  private baseURL2 = "http://localhost:8088/Address"

  createAddress(address:Address,id:number):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/${id}`,address);
  }

  getAddressList():Observable<Address[]>{
    return this.httpClient.get<Address[]>(`${this.baseURL}`);
  }

  getAddressById(id:number):Observable<Address>{
    return this.httpClient.get<Address>(`${this.baseURL2}/${id}`);
  }

  updateAddress(addId:any,empId:any, address:Address):Observable<object>{

    return this.httpClient.put(`${this.baseURL}/${addId}/${empId}`, address);
  
  }

  deleteAddress(id:any):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

}
