package com.ale.config.ssm;


import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
/**
  * @author alewu
  * @date 2018/2/8
  * @description 根应用配置
  */
@Configuration
@ComponentScan(basePackages = {"com.ale.service"}, excludeFilters = @Filter(Controller.class))
public class AppConfig {
    @Bean
    public BeanNameAutoProxyCreator proxyCreator(){
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        proxyCreator.setBeanNames("*ServiceImpl");
        proxyCreator.setInterceptorNames("transactionInterceptor");
        return proxyCreator;
    }

}
