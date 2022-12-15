package com.hostbooks.Service;

import com.hostbooks.Dto.AddressDto;
import com.hostbooks.entities.Address;
import com.hostbooks.entities.Employee;
import com.hostbooks.exceptions.ResourceNotFoundException;
import com.hostbooks.repository.AddressDao;
import com.hostbooks.repository.EmployeeDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private ModelMapper mp;
    @Autowired
    private EmployeeDao empDao;
    @Autowired
    private AddressDao addDao;
    @Override
    public AddressDto createAddress(AddressDto addressDto, Integer empId) {

        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));

        Address address = mp.map(addressDto,Address.class);

        address.setEmployee(employee);

        Address savedAddress = addDao.save(address);

        return mp.map(savedAddress,AddressDto.class);


    }

    @Override
    public AddressDto updateAddress(Integer addId, Integer empId, AddressDto addressDto) throws ResourceNotFoundException {

        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));


        Address address = addDao.findById(addId).orElseThrow(()-> new ResourceNotFoundException("Address does not exist with addressId"+" "+addId));

        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setEmployee(employee);

        Address updatedAddress = addDao.save(address);

        return mp.map(updatedAddress,AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddressByEmployeeId(Integer empId) throws ResourceNotFoundException {

        Employee employee = empDao.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with employee id"+" "+empId));

        List<Address> addresses = addDao.findByEmployee(employee);

        List<AddressDto> addressDtos =addresses.stream().map((address) -> mp.map(address,AddressDto.class)).collect(Collectors.toList());
        return addressDtos;
    }

    @Override
    public AddressDto getAddressById(Integer addId) throws ResourceNotFoundException {

        Address address = addDao.findById(addId).orElseThrow(()-> new ResourceNotFoundException("Address does not exist with addressId"+" "+addId));

        return mp.map(address,AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addDao.findAll();
        List<AddressDto> addressDtos =addresses.stream().map((address) -> mp.map(address,AddressDto.class)).collect(Collectors.toList());
        return addressDtos;


    }

    @Override
    public void deleteAddress(Integer addId) throws ResourceNotFoundException {

        Address address = addDao.findById(addId).orElseThrow(()-> new ResourceNotFoundException("Address does not exist with addressId"+" "+addId));

        addDao.delete(address);


    }


}
