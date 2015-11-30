package hu.innocenter.bigdata;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */

@Configuration
@EnableScheduling
@ComponentScan(basePackages="hu.innocenter.bigdata.workers")
public class ApplicationConfig {

    public enum MODE {CEMENT, LASER}

    public static MODE mode = MODE.CEMENT;

    public static String layout = mode == MODE.LASER ? "layout-laser" : "layout-cement";

}
