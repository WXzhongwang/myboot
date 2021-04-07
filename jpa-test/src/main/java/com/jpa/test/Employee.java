package com.jpa.test;

import javax.persistence.*;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/7
 */
@Entity(name = "ht_employee")
public class Employee {
    /**
     * @Id注解表示这个字段是一个id，
     * @GeneratedValue注解表示主键的自增长策略
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "address")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
