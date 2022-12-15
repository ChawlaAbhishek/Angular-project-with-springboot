package com.hostbooks.Dto;

import com.hostbooks.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Integer empId;
    private String firstName;
    private String lastName;

    private String mobileNumber;
  //  private List<Address> addresses = new ArrayList<>();
}
