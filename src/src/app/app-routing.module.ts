import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
//import { CreateAddressComponent } from './create-address/create-address.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { AddressListComponent } from './address-list/address-list.component';
import { UpdateAddressComponent } from './update-address/update-address.component';
import { CreateDesignationComponent } from './create-designation/create-designation.component';
import { DesignationListComponent } from './designation-list/designation-list.component';
import { UpdateDesignationComponent } from './update-designation/update-designation.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {path:'employees', canActivate:[AuthGuard],component:EmployeeListComponent},
  {path:'create-employee',component:CreateEmployeeComponent},
  {path:'',redirectTo:'login',pathMatch:'full'},
  {path:'update-employee/:id',component:UpdateEmployeeComponent},
  {path:'employee-details/:id',component:EmployeeDetailsComponent},
 // {path:'create-address/:id',component:CreateAddressComponent},
  {path:'addresses',component:AddressListComponent},
  {path:'update-address/:addId/:empId',component:UpdateAddressComponent},
  {path:'create-designation/:empId',component:CreateDesignationComponent},
  {path:'designations',component:DesignationListComponent},
  {path:'update-designation/:degId/:empId',component:UpdateDesignationComponent},
  {path:'login',component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
