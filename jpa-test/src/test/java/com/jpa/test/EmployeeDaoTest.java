package com.jpa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void findByEmployeeByNameOrEnglishName() {
        Employee employee = employeeDao.findByNameOrEnglishName("钟盛旺", "dick");
        System.out.println(employee.getAddress() + employee.getEnglishName() + employee.getName());
    }

    @Test
    public void maxIdEmployee() {
        System.out.println(employeeDao.maxIdEmployee());
    }
}