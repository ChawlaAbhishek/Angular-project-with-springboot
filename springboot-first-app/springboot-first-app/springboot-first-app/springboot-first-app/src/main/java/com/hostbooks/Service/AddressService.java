package com.hostbooks.Service;

import com.hostbooks.Dto.AddressDto;
import com.hostbooks.entities.Address;
import com.hostbooks.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto addressDto,Integer empId);
    AddressDto updateAddress(Integer addId,Integer empId,AddressDto addressDto) throws ResourceNotFoundException;
   // List<AddressDto> getAllAddressByEmployeeId(Integer empId) throws ResourceNotFoundException;
    AddressDto getAddressById(Integer addId) throws ResourceNotFoundException;

    List<AddressDto> getAllAddresses();
    void deleteAddress(Integer addId) throws ResourceNotFoundException;
}
