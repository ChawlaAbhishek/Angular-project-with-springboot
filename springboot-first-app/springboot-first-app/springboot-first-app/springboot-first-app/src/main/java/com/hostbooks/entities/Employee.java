package com.hostbooks.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer empId;
    private String  firstName;
    private String  lastName;
    private String  mobileNumber;
    @OneToMany(cascade = CascadeType.ALL)
     private List<Address> addresses= new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Designation designation;


}
