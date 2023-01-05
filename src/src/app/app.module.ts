import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import{FormsModule} from '@angular/forms';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
//import { CreateAddressComponent } from './create-address/create-address.component';
import { AddressListComponent } from './address-list/address-list.component';
import { UpdateAddressComponent } from './update-address/update-address.component';
import { CreateDesignationComponent } from './create-designation/create-designation.component';
import { DesignationListComponent } from './designation-list/designation-list.component';
import { UpdateDesignationComponent } from './update-designation/update-designation.component'
import { NgxPaginationModule } from 'ngx-pagination';
import { LoginComponent } from './login/login.component';
import { AuthInterceptorInterceptor } from './auth-interceptor.interceptor';
import { AuthGuard } from './auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    CreateEmployeeComponent,
    UpdateEmployeeComponent,
    EmployeeDetailsComponent,
   // CreateAddressComponent,
    AddressListComponent,
    UpdateAddressComponent,
    CreateDesignationComponent,
    DesignationListComponent,
    UpdateDesignationComponent,
    LoginComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [
    AuthGuard,
    LoginComponent,
    {provide:HTTP_INTERCEPTORS,useClass:AuthInterceptorInterceptor,multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
