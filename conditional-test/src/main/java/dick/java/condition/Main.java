package dick.java.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/4/30
 */
public class Main {
    /**
     * 核心思想就是： 使用 @Conditional 注解当满足某种条件的时候，某个 Bean 才会生效，而正是这一特性，支撑起了 Spring Boot 的自动化配置。
     *
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().getSystemProperties().put("people", "北方人");
        ctx.register(JavaConfig.class);
        ctx.refresh();
        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName());
    }
}
