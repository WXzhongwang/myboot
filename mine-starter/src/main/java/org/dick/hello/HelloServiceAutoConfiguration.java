package org.dick.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description 自动配置类
 * 首先 @Configuration 注解表明这是一个配置类。
 * @EnableConfigurationProperties 注解是使我们之前配置的 @ConfigurationProperties 生效，让配置的属性成功的进入 Bean 中。
 * @ConditionalOnClass 表示当项目当前 classpath 下存在 HelloService 时，后面的配置才生效。
 * 自动配置类中首先注入 HelloProperties ，这个实例中含有我们在 application.properties 中配置的相关数据。
 * 提供一个 HelloService 的实例，将 HelloProperties 中的值注入进去。
 *
 *
 * @date created on 2020/4/30
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnClass(HelloService.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    HelloProperties helloProperties;

    @Bean
    HelloService helloService(){
        HelloService helloService = new HelloService();
        helloService.setMsg(helloProperties.getMsg());
        helloService.setName(helloProperties.getName());
        return helloService;
    }
}
