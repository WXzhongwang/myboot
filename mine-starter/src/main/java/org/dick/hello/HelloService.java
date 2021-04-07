package org.dick.hello;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/4/30
 */
public class HelloService {
    private String msg;
    private String name;
    public String sayHello() {
        return name + " say " + msg + " !";
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
