package com.hostbooks.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignationDto {

    private Integer degId;
    private String grade;
//    @JsonIgnore
//    private EmployeeDto employee;
}
