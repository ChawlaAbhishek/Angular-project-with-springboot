package com.hostbooks.repository;

import com.hostbooks.entities.Employee;

import java.util.List;

public interface CustomEmployeeDao {

    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeByFirstNameAndDesignation(String firstName,String designation);

    List<Employee> findEmployeeInDescendingOrder();

    List<Employee> filterEmployeeBaseOnIdAndName(Integer empId,String name);

    boolean findEmployeeByMobile(String mobileNumber);
}