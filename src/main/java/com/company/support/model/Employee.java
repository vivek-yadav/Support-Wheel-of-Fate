package com.company.support.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    @JsonBackReference
    private List<Shift> shifts;
}