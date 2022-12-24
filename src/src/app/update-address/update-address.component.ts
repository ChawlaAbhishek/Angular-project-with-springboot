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

  addId:any;
  empId:any;
  address:Address = new Address();


  constructor(private addressService:AddressService,private router:Router,private route:ActivatedRoute){

  }

  ngOnInit(): void {
    this.addId=this.route.snapshot.params['addId'];
    this.empId=this.route.snapshot.params['empId'];
 //   console.log(this.id);
// console.log(this.employee.id);
    this.addressService.getAddressById(this.addId).subscribe(data=>{
      this.address=data;
    })
    
  }

  onSubmit(){
    //console.log(this.address);
    //console.log(this.employee.id);
    this.addressService.updateAddress(this.addId,this.empId,this.address).subscribe(data=>{
      console.log(data);
      this.goToAddressList();
    })


  }
  goToAddressList(){
    this.router.navigate(['addresses']);

  }

}
