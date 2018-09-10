package com.company.support.model;

import java.time.LocalDate;

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
public class Shift {
    // generates "yyyy-MM-dd" output
    @JsonSerialize(using = ToStringSerializer.class)
    // handles "yyyy-MM-dd" input just fine
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private Integer shiftOrderNo;
    private ShiftType shiftType;
    private Employee employee;
}