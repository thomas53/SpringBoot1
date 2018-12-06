package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	public Employee findByNik(String nik);
	
	@Query("SELECT AVG(e.pointNumber) FROM Employee e ")
	public Double average();
}
