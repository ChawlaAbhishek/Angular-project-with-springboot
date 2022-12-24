package com.hostbooks.repository;

import com.hostbooks.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao  extends JpaRepository<Employee,Integer> {
}
