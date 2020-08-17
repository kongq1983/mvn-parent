package com.kq.mvn.acccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author kq
 * @date 2020-08-17 14:00
 * @since 2020-0630
 */

@SpringBootApplication
public class AccountApplication {

    protected static Logger logger = LoggerFactory.getLogger(AccountApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AccountApplication.class, args);

//        String[] beanNames = context.getBeanDefinitionNames();
//
//        for(String beanName : beanNames) {
//            logger.info("load beanName ={}",beanName);
//        }
//
//        logger.info("load beanNames size ={}",context.getBeanDefinitionCount());

    }


}
