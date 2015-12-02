package hu.innocenter.bigdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Ákos Kiszely on 2015.12.01..
 * akos.kiszely@gmail.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password, enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("akosk").password("123456").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/data**", "/calc**", "/index**").access("hasRole('ROLE_USER')")
//                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().csrf().disable();
        /*
        http.authorizeRequests()
                .antMatchers("/data**").access("hasRole('ROLE_USER')")
                .antMatchers("/calc**").access("hasRole('ROLE_USER')")
                .antMatchers("/index**").access("hasRole('ROLE_USER')")
                .antMatchers("/").access("hasRole('ROLE_USER')")
//                .antMatchers("/webapi/**").access("hasRole('ROLE_USER')")
//                .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
                .and()
                .formLogin() ;
//                .loginPage("/login")
//                .permitAll();
//                .and()
//                .logout()
//                .permitAll();
          */
    }
}
