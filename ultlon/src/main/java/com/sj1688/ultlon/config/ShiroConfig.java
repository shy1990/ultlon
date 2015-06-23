package com.sj1688.ultlon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value="classpath:shiro-config.xml")
public class ShiroConfig {

}
