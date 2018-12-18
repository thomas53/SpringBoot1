package com.employee.app.repository;

import com.employee.app.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Before
    public void initialize(){
        employeeRepository.save(new Employee(1,"10022001","Tayo1",55.0,new Date()));
        employeeRepository.save(new Employee(2,"10022002","Tayo2",80.0,new Date()));
        employeeRepository.save(new Employee(3,"10022004","Tayo4",75.0,new Date()));
    }

    @Test
    public void testFindAll(){
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(3,employees.size());
    }

    @Test
    public void testFindNik(){
        Employee employee = employeeRepository.findByNik("10022002");
        assertEquals("Tayo2",employee.getName());
    }

    @Test
    public void testFindAverage(){
        Double avg = employeeRepository.average();
        assertEquals(70,avg,0.00001);
    }

    
}
