package com.security.test;

import com.security.test.config.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/12
 */
@Configuration
public class QualifierTests {

    @Autowired
    @Qualifier
    private VerifyCode verifyCode;

}
