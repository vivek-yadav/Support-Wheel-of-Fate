package com.company.support;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.company.support.model.Schedule;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void generateSchedule() throws Exception {
        List<DayOfWeek> off = new ArrayList<DayOfWeek>();
        off.add(DayOfWeek.SUNDAY);
        off.add(DayOfWeek.SATURDAY);
        String result = "{\"name\":\"first-schedule\",\"startDate\":\"2018-09-10\",\"noOfWorkingDays\":10,\"noOfEmployees\":10,\"minRestShift\":4,\"noOfShiftsPerEmployee\":2,\"offDaysOfWeek\":[\"SUNDAY\",\"SATURDAY\"],\"shifts\":[{\"date\":\"2018-09-10\",\"shiftOrderNo\":0,\"shiftType\":\"DAY\",\"employee\":{\"id\":0,\"name\":\"EMP_0\"}},{\"date\":\"2018-09-10\",\"shiftOrderNo\":1,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":1,\"name\":\"EMP_1\"}},{\"date\":\"2018-09-11\",\"shiftOrderNo\":2,\"shiftType\":\"DAY\",\"employee\":{\"id\":2,\"name\":\"EMP_2\"}},{\"date\":\"2018-09-11\",\"shiftOrderNo\":3,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":3,\"name\":\"EMP_3\"}},{\"date\":\"2018-09-12\",\"shiftOrderNo\":4,\"shiftType\":\"DAY\",\"employee\":{\"id\":4,\"name\":\"EMP_4\"}},{\"date\":\"2018-09-12\",\"shiftOrderNo\":5,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":5,\"name\":\"EMP_5\"}},{\"date\":\"2018-09-13\",\"shiftOrderNo\":6,\"shiftType\":\"DAY\",\"employee\":{\"id\":6,\"name\":\"EMP_6\"}},{\"date\":\"2018-09-13\",\"shiftOrderNo\":7,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":7,\"name\":\"EMP_7\"}},{\"date\":\"2018-09-14\",\"shiftOrderNo\":8,\"shiftType\":\"DAY\",\"employee\":{\"id\":8,\"name\":\"EMP_8\"}},{\"date\":\"2018-09-14\",\"shiftOrderNo\":9,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":9,\"name\":\"EMP_9\"}},{\"date\":\"2018-09-17\",\"shiftOrderNo\":10,\"shiftType\":\"DAY\",\"employee\":{\"id\":0,\"name\":\"EMP_0\"}},{\"date\":\"2018-09-17\",\"shiftOrderNo\":11,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":1,\"name\":\"EMP_1\"}},{\"date\":\"2018-09-18\",\"shiftOrderNo\":12,\"shiftType\":\"DAY\",\"employee\":{\"id\":2,\"name\":\"EMP_2\"}},{\"date\":\"2018-09-18\",\"shiftOrderNo\":13,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":3,\"name\":\"EMP_3\"}},{\"date\":\"2018-09-19\",\"shiftOrderNo\":14,\"shiftType\":\"DAY\",\"employee\":{\"id\":4,\"name\":\"EMP_4\"}},{\"date\":\"2018-09-19\",\"shiftOrderNo\":15,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":5,\"name\":\"EMP_5\"}},{\"date\":\"2018-09-20\",\"shiftOrderNo\":16,\"shiftType\":\"DAY\",\"employee\":{\"id\":6,\"name\":\"EMP_6\"}},{\"date\":\"2018-09-20\",\"shiftOrderNo\":17,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":7,\"name\":\"EMP_7\"}},{\"date\":\"2018-09-21\",\"shiftOrderNo\":18,\"shiftType\":\"DAY\",\"employee\":{\"id\":8,\"name\":\"EMP_8\"}},{\"date\":\"2018-09-21\",\"shiftOrderNo\":19,\"shiftType\":\"NIGHT\",\"employee\":{\"id\":9,\"name\":\"EMP_9\"}}]}";

        Schedule user = new Schedule("first-schedule", LocalDate.parse("2018-09-10"), 10, 10, 4, 2, off, null);
        ResponseEntity<String> response = template.postForEntity(base.toString() + "schedule/generate", user,
                String.class);
        assertThat(response.getBody(), equalTo(result));
    }
}