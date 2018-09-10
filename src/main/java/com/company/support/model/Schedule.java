package com.company.support.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    String name;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate startDate;
    Integer noOfWorkingDays;
    Integer noOfEmployees;
    Integer minRestShift = 1;
    Integer noOfShiftsPerEmployee = 2;
    List<DayOfWeek> offDaysOfWeek;
    List<Shift> shifts;
}