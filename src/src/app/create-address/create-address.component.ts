// import { Component,OnInit } from '@angular/core';
// import { Router, TitleStrategy } from '@angular/router';
// import { Address } from '../address';
// import { AddressService } from '../address.service';
// import { ActivatedRoute } from '@angular/router';

// @Component({
//   selector: 'app-create-address',
//   templateUrl: './create-address.component.html',
//   styleUrls: ['./create-address.component.css']
// })
// export class CreateAddressComponent implements OnInit{

//   address:Address = new Address();
//   id:any;
  
//   ngOnInit(){

//     this.id=this.route.snapshot.params['id'];
//     console.log(this.id);


//   }
  
//   constructor(private addressService:AddressService,private router:Router,private route:ActivatedRoute){}

//   onSubmit(){
//    // console.log(this.address);
//    // console.log(this.employee.id);
   
//     this.addressService.createAddress(this.address,this.id).subscribe(data=>{
//       console.log("data"+data);
//       this.goToAddressList();

//     })
//     }

//     goToAddressList(){
//       this.router.navigate(['/addresses']);


//     }

//     // updateAddress(){
//     //   this.router.navigate(['update-address/:addId'])
//     // }

// }
