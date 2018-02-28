package com.ale.config;


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
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ale.service"}, excludeFilters = @Filter(Controller.class))
public class AppConfig {

}
