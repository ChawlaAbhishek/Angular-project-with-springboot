package com.hostbooks.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer degId;

    private String grade;
    //@OneToOne
//    @JsonIgnore
//    private Employee employee;
}
