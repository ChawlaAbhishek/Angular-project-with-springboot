package com.hostbooks.Controller;

import com.hostbooks.Dto.AddressDto;
import com.hostbooks.Service.AddressService;
import com.hostbooks.Validator.AddressValidator;
import com.hostbooks.Validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200/")
@RestController
public class AddressController {

    @Autowired
    AddressService addService;

    @PostMapping("/Addresses/{empId}")
    ResponseEntity<AddressDto> createAddressHandler(@RequestBody AddressDto addressDto, @PathVariable Integer empId, BindingResult valid){

        new AddressValidator().validate(addressDto,valid);

        AddressDto savedAddress = addService.createAddress(addressDto,empId);

        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);

    }

    @PutMapping("/Addresses/{addId}/{empId}")
    ResponseEntity<AddressDto> updateAddressHandler(@RequestBody AddressDto addressDto,@PathVariable Integer addId,@PathVariable Integer empId,BindingResult valid){

        new AddressValidator().validate(addressDto,valid);

        AddressDto updatedAddress = addService.updateAddress(addId,empId,addressDto);

        return new ResponseEntity<>(updatedAddress,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/Addresses/{addId}")
    String deleteAddressByEmployeeId(@PathVariable Integer addId){
        addService.deleteAddress(addId);
        return "address deleted successfully";
    }

    @GetMapping("/Address/{addId}")
    ResponseEntity<AddressDto> getAddressByIdHanlder(@PathVariable Integer addId){

        AddressDto addressDto =addService.getAddressById(addId);

        return new ResponseEntity<>(addressDto,HttpStatus.ACCEPTED);



    }
//    @GetMapping("/Addresses/{empId}")
//    ResponseEntity<List<AddressDto>> getAllAddressesByEmployeeHandler(@PathVariable Integer empId){
//
//        List<AddressDto> addressDtos= addService.getAllAddressByEmployeeId(empId);
//
//        return new ResponseEntity<>(addressDtos,HttpStatus.ACCEPTED);
//
//    }
    @GetMapping("/Addresses")
    ResponseEntity<List<AddressDto>> getAllAddressesHandler(){

        List<AddressDto> addressDtos= addService.getAllAddresses();

        return new ResponseEntity<>(addressDtos,HttpStatus.ACCEPTED);

    }
}
