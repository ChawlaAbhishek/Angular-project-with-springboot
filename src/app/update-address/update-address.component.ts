import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Address } from '../address';
import { AddressService } from '../address.service';
import { Employee } from '../employee';

@Component({
  selector: 'app-update-address',
  templateUrl: './update-address.component.html',
  styleUrls: ['./update-address.component.css']
})
export class UpdateAddressComponent implements OnInit{

  id:any;
  address:Address = new Address();
  employee:Employee=new Employee();


  constructor(private addressService:AddressService,private router:Router,private route:ActivatedRoute){

  }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['addId'];
 //   console.log(this.id);
// console.log(this.employee.id);
    this.addressService.getAddressById(this.id).subscribe(data=>{
      this.address=data;
    })
    
  }

  onSubmit(){
    console.log(this.address);
    console.log(this.employee.id);
    this.addressService.updateAddress(this.id,this.employee.id,this.address).subscribe(data=>{
      console.log(data);
      this.goToAddressList();
    })


  }
  goToAddressList(){
    this.router.navigate(['addresses']);

  }

}
