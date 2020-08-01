package com.programmingsharing.qbedemo.services;

import com.programmingsharing.qbedemo.EmployeeRepository;
import com.programmingsharing.qbedemo.domain.EmployeeSearchDto;
import com.programmingsharing.qbedemo.entities.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employees> getEmployeesByExample(EmployeeSearchDto employeeSearchDto){
        Employees employeesAsProbe = new Employees();
        employeesAsProbe.setGender(employeeSearchDto.getGender());
        employeesAsProbe.setLastName(employeeSearchDto.getLastName());
        employeesAsProbe.setFirstName(employeeSearchDto.getFirstName());
//        employeesAsProbe.setBirthDate(defaultIfnew java.sql.Date(employeeSearchDto.getDob().getTime()));
//        employeesAsProbe.setHireDate(new java.sql.Date(employeeSearchDto.getHireDate().getTime()));


        ExampleMatcher employeeMatcher = ExampleMatcher.matchingAll().withIgnoreCase("lastName", "firstName")
                .withIgnorePaths("empNo")
                .withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return employeeRepository.findAll(Example.of(employeesAsProbe, employeeMatcher));
    }
}