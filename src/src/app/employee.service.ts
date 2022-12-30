import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { Employee } from './employee';
import { EmployeeResponse } from './employee-response';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8088/Employees";

  constructor(private httpClient:HttpClient) { }

  getEmployeesList():Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseURL}`);
  }

  createEmployee(employee:Employee):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`,employee);
  }

  getEmployeeById(id:number):Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseURL}/${id}`);
  }

  updateEmployee(id:any, employee:Employee):Observable<object>{

    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  
  }

  deleteEmployee(id:any):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
  getEmployeeByPagination(name:any,pageNumber:any,pageSize:any,sortBy:any,sortDir:any):Observable<EmployeeResponse>{

    return this.httpClient.get<EmployeeResponse>(`${this.baseURL}?name=${name}&pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=${sortBy}&sortDir=${sortDir}`);



  }

}
