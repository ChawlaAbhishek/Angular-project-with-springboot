import { Injectable } from '@angular/core';
import { Designation } from './designation';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DesignationService {

  constructor(private httpClient:HttpClient) { }

  private baseURL = "http://localhost:8088/designations";

  getDesignationList():Observable<Designation[]>{
    return this.httpClient.get<Designation[]>(`${this.baseURL}`);
  }

  createDesignation(designation:Designation,id:number):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/${id}`,designation);
  }

  getDesignationById(id:number):Observable<Designation>{
    return this.httpClient.get<Designation>(`${this.baseURL}/${id}`);
  }

  updateDesignation(designation:Designation,empId:any,degId:any):Observable<object>{

    return this.httpClient.put(`${this.baseURL}/${empId}/${degId}`, designation);
  
  }

  deleteDesignation(degId:any):Observable<object>{

    return this.httpClient.delete(`${this.baseURL}/${degId}`);

  }



}
