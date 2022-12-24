package com.hostbooks.repository;

import com.hostbooks.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationDao extends JpaRepository<Designation,Integer> {
}
