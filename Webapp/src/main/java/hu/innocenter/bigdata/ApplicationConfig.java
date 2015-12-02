package hu.innocenter.bigdata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */

@Configuration
@EnableScheduling
@ComponentScan(basePackages="hu.innocenter.bigdata.workers")
@Import({ SecurityConfig.class })
public class ApplicationConfig {

    public enum MODE {CEMENT, LASER}

    public static MODE mode = MODE.CEMENT;

    public static String layout = mode == MODE.LASER ? "layout-laser" : "layout-cement";

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver
//                = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/pages/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
}
