package com.jpa.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/7
 */
public interface EmployeeDao extends JpaRepository<Employee, Integer>{

    /**通过姓名或英文姓名查找
     * @param name 姓名
     * @param englishName 英文姓名
     * @return Employee Object
     */
    Employee findByNameOrEnglishName(String name, String englishName);

    /**
     * 获取最大工号员工
     * @return Employee Object
     */
    @Query(value = "select * from ht_employee where id=(select max(id) from ht_employee)",nativeQuery = true)
    Employee maxIdEmployee();
}
