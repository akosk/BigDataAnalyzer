package hu.innocenter.bigdata;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */

public class ApplicationConfig {

    public enum MODE {CEMENT, LASER}

    public static MODE mode = MODE.CEMENT;

    public static String layout = mode == MODE.LASER ? "layout-laser" : "layout-cement";

}
