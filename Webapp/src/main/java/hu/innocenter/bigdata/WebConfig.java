package hu.innocenter.bigdata;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ákos Kiszely on 2015.10.29..
 * akos.kiszely@gmail.com
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "hu.innocenter.bigdata")
public class WebConfig {
}
