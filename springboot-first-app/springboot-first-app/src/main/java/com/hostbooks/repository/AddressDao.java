package com.hostbooks.repository;

import com.hostbooks.entities.Address;
import com.hostbooks.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressDao extends JpaRepository<Address,Integer> {

    List<Address> findByEmployee(Employee employee);
}
