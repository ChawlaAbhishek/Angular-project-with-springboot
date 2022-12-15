import { Component,OnInit } from '@angular/core';
import { Address } from '../address';
import { AddressService } from '../address.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  addresses:any=Address;

  ngOnInit(){
    this.getAddressList();

  }
  constructor(private addressService:AddressService,private router:Router){}

  getAddressList(){
    this.addressService.getAddressList().subscribe(data=>{
      this.addresses=data;
      
    })
    this.router.navigate(["addresses"]);


  }
  updateAddress(addId:any){

   // console.log(id);
    this.router.navigate(["update-address",addId]);

  }

  deleteAddress(addId:any){

    this.addressService.deleteAddress(addId).subscribe(data=>{
       console.log(data);

    })
    window.location.reload();
}

}
