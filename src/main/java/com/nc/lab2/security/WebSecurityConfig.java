package com.nc.lab2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Class that response for Security access in application
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Method which specify users roles.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users =  User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("admin").password("admin123").roles("ADMIN").build());
        manager.createUser(users.username("user").password("user123").roles("USER").build());
        return manager;
    }

    /**
     *  Method that configure access for pages
     * @param http request.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index", "/").permitAll()
                .antMatchers("/viewAllStudents").authenticated()
                .antMatchers("/viewAllGroups").authenticated()
                .antMatchers("/viewAllFacultys").authenticated()
                .antMatchers("/viewAllSubject").authenticated()
                .antMatchers("/studentMarkInfo/{id}").authenticated()
                .antMatchers("/addStudent").hasRole("ADMIN")
                .antMatchers("/addFaculty").hasRole("ADMIN")
                .antMatchers("/addGroup").hasRole("ADMIN")
                .antMatchers("/studentMarkInfo/addMark/{studentId}").hasRole("ADMIN")
                .antMatchers("/studentMarkInfo/deleteMark/{id}").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));


    }
}
