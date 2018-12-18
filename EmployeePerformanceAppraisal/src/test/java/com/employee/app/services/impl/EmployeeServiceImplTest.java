package com.employee.app.services.impl;

import com.employee.app.entity.Employee;
import com.employee.app.message.AllEmployeeResponse;
import com.employee.app.message.DetailEmployeeResponse;
import com.employee.app.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.omg.CORBA.Environment;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Environment env;

    @Test
    public void inquiryAllEmployeeService() {
        Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList(
                //(Integer id, @NotNull String nik, String name, @NotNull Double pointNumber, Date dateOfBirth)
                new Employee(1,"10022001","tayo1",60.0,new Date()),
                new Employee(2,"10022002","tayo2",80.0,new Date()),
                new Employee(3,"10022003","tayo3",85.0,new Date())
        ));

        AllEmployeeResponse allEmployeeResponse = employeeService.inquiryAllEmployeeService();

        assertEquals("1",allEmployeeResponse.getResponseCode());
        assertEquals(3, allEmployeeResponse.getEmployees().size());
    }

    @Test
    public void inquiryDetailEmployeeService() {
        Mockito.when(employeeRepository.findByNik("100250002")).thenReturn(new Employee(2,"100250002","Tayo2",60.0,new Date()));

        DetailEmployeeResponse detailEmployeeResponse = employeeService.inquiryDetailEmployeeService("100250002");

        assertEquals("1",detailEmployeeResponse.getResponseCode());
        assertEquals("Tayo2",detailEmployeeResponse.getEmployee().getName());
    }

}