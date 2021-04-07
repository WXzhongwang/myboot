package com.mail.test;

import javax.xml.bind.annotation.XmlElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description MemberInfo
 * @date created on 2020/5/11
 */
public class Member {

    private String username;

    private Date birthday;

    private String email;

    private Integer year;

    private String imgBase64;

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public Integer getYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str1 = sdf.format(birthday);
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(str1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aft.setTime(new Date());
        int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);
        return Integer.valueOf(year);
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @XmlElement(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name="username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name="birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", year=" + year +
                ", imgBase64='" + imgBase64 + '\'' +
                '}';
    }
}
