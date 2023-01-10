package com.hostbooks.Dto;

import com.hostbooks.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Integer addId;
    private String city;
    private String state;
    private String country;
   // private EmployeeDto employee;


}
