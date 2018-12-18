package com.employee.app.controller;

import com.employee.app.message.AllEmployeeResponse;
import com.employee.app.message.DetailEmployeeResponse;
import com.employee.app.message.EmployeeMessage;
import com.employee.app.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    private String getObjtoJson(Object request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writerWithDefaultPrettyPrinter();
        return ow.writeValueAsString(request );
    }

    @Test
    public void callInquiryAllDataService_basic() throws Exception {
        AllEmployeeResponse response = new AllEmployeeResponse();
        response.setResponseCode("1");
        response.setResponseMessage("Success");

        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");

        EmployeeMessage employeeMessage = new EmployeeMessage("212412","tayo",80.0,"A",sd.parse("19930423"));
        List<EmployeeMessage> messages = new ArrayList<>();
        messages.add(employeeMessage);

        response.setEmployees(messages);

        System.out.println(getObjtoJson(response));

        when(employeeService.inquiryAllEmployeeService())
                .thenReturn(response);

        this.mvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().json(getObjtoJson(response)));
    }

    @Test
    public void callInquiryDetailEmployeeService_basic() throws Exception {
        DetailEmployeeResponse response = new DetailEmployeeResponse();
        response.setResponseCode("1");
        response.setResponseMessage("Success");

        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");

        EmployeeMessage employeeMessage = new EmployeeMessage("212412","tayo",80.0,"A",sd.parse("19930423"));
        response.setEmployee(employeeMessage);

        System.out.println(getObjtoJson(response));

        when(employeeService.inquiryDetailEmployeeService("212412"))
                .thenReturn(response);

        this.mvc.perform(get("/employee/212412"))
                .andExpect(status().isOk())
                .andExpect(content().json(getObjtoJson(response)));
    }





}
