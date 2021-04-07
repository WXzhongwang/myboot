package dick.java.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * 两个 Bean 的名字都为 food，这不是巧合，而是有意取的。两个 Bean 的返回值都为其父类对象 Food。
 * 每个 Bean 上都多了 @Conditional 注解，当 @Conditional 注解中配置的条件类的 matches 方法返回值为 true 时，对应的 Bean 就会生效。
 * @date created on 2020/4/30
 */
@Configuration
public class JavaConfig {
    @Bean("food")
    @Conditional(RiceCondition.class)
    Food rice() {
        return new Rice();
    }
    @Bean("food")
    @Conditional(NoodlesCondition.class)
    Food noodles() {
        return new Noodles();
    }
}