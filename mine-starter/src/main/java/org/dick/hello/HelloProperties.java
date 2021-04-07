package org.dick.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/4/30
 */
@ConfigurationProperties(prefix = "dick")
public class HelloProperties {
    private static final String DEFAULT_NAME = "咸鱼";
    private static final String DEFAULT_MSG = "加油Fighting!!!";
    private String name = DEFAULT_NAME;
    private String msg = DEFAULT_MSG;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
