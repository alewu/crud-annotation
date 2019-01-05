package com.ale.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ale.service"}, excludeFilters = @Filter(Controller.class))
public class AppConfig {

}
