package com.company.support.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.company.support.algo.Hungarian;
import com.company.support.model.Employee;
import com.company.support.model.Schedule;
import com.company.support.model.Shift;
import com.company.support.model.ShiftType;

import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public Schedule generateSchedule(Schedule schedule) {
        LocalDate d;
        Integer day;
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        if (schedule.getNoOfWorkingDays() > 0) {
            schedule.setShifts(new ArrayList<>());
        }
        for (d = schedule.getStartDate(), day = 0; day < schedule.getNoOfWorkingDays() * ShiftType.values().length;) {
            if (isOffDay(d, schedule.getOffDaysOfWeek())) {
                d = d.plusDays(1);
                continue;
            }

            Shift shift = new Shift();
            shift.setDate(d);
            shift.setShiftOrderNo(day);
            shift.setShiftType(ShiftType.values()[day % ShiftType.values().length]);
            schedule.getShifts().add(shift);

            if (day % ShiftType.values().length == ShiftType.values().length - 1) {
                d = d.plusDays(1);
            }
            day++;
        }

        double[][] workMatrix = fillMatrix(schedule.getShifts(), schedule.getMinRestShift());
        Hungarian solver = new Hungarian(workMatrix);
        int[] result = solver.execute();
        List<Employee> employees = new ArrayList<>();
        for (int i = employees.size(); i < schedule.getNoOfEmployees(); i++) {
            Employee e = new Employee();
            e.setId(i);
            e.setName("EMP_" + i);
            e.setShifts(new ArrayList<>());
            employees.add(e);
        }
        for (int i = 0; i < result.length; i++) {
            employees.get(i % employees.size()).getShifts().add(schedule.getShifts().get(i));
            schedule.getShifts().get(i).setEmployee(employees.get(i % employees.size()));

            // System.out.print(
            // schedule.getShifts().get(i).getDate() +
            // schedule.getShifts().get(i).getShiftType().toString());
            // System.out.println(" : " + schedule.getShifts().get(result[i]).getDate()
            // + schedule.getShifts().get(result[i]).getShiftType().toString());
        }

        return schedule;
    }

    private boolean isOffDay(LocalDate date, List<DayOfWeek> offDaysOfWeek) {
        for (DayOfWeek d : offDaysOfWeek) {
            if (d == date.getDayOfWeek()) {
                return true;
            }
        }
        return false;
    }

    private double[][] fillMatrix(List<Shift> shifts, Integer minRestShift) {
        double[][] M = new double[shifts.size()][shifts.size()];
        for (int r = 0; r < M.length; r++) {
            for (int c = 0; c < M[r].length; c++) {
                M[r][c] = shifts.get(c).getShiftOrderNo() - shifts.get(r).getShiftOrderNo();
                if (M[r][c] < minRestShift) {
                    M[r][c] = M.length + M[r][c];
                }
            }
        }
        return M;
    }
}