package com.codegym.case_study_team_2_study.config;

import com.codegym.case_study_team_2_study.service.account.AccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountDetailsServiceImpl accountDetailsService;
    @Autowired
    private DataSource dataSource;

    public BCryptPasswordEncoder passwordEncoder() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder());

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/account/signup", "/account/login", "/home", "/account/logoutSuccessful").permitAll();
        http.authorizeRequests().antMatchers("/account").authenticated();
        // các trang cần login
        http.authorizeRequests().antMatchers("/success").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/success/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        // account
        http.authorizeRequests().antMatchers("/account").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/delete").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/add").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/change-password").permitAll();
        http.authorizeRequests().antMatchers("/account/forgot").permitAll();
        http.authorizeRequests().antMatchers("/account/create").permitAll();
        http.authorizeRequests().antMatchers("/account/confirm").permitAll();
        http.authorizeRequests().antMatchers("/account/confirm-code").permitAll();
        // teacher
        http.authorizeRequests().antMatchers("/teachers").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teachers/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teachers/create").access("hasAnyRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/teachers/create/").access("hasAnyRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/teachers/save").access("hasAnyRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/teachers/edit/{id}").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teachers/edit/{id}/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teachers/update").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teachers/update/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teachers/delete").access("hasAnyRole('ROLE_ADMIN')");
        //  student
        http.authorizeRequests().antMatchers("/student").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/student/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/student/add").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/student/add/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/student/delete").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/student/edit/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/student/edit").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        //  module
        http.authorizeRequests().antMatchers("/module").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/delete").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/delete/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/create").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/create/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/save").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/save/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/edit/{id}").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/edit/{id}/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/edit").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/edit/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/detail/{id}").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/module/detail/{id}/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");

        //point
        http.authorizeRequests().antMatchers("/point").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/delete").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/create").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/create/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/save").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/save/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/edit/{id}").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/edit/{id}/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/edit").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/point/edit/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        //document
        http.authorizeRequests().antMatchers("/document").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/delete").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/delete/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/create").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/create/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/save").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/save/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/edit/{id}").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/edit/{id}/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/edit").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/edit/").access("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/detail/{id}").access("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/document/detail/{id}/").access("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN', 'ROLE_TEACHER')");
        // assignment
        http.authorizeRequests().antMatchers("/assignment/list").access("hasAnyRole( 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/assignment/list/").access("hasAnyRole( 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/assignment/create/").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/assignment/create").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/assignment/update").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/assignment/update/").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/assignment/edit").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/assignment/edit/").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/assignment/delete").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/assignment/delete/").access("hasAnyRole( 'ROLE_ADMIN')");
        //class
        http.authorizeRequests().antMatchers("/classes").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/classes/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/classes/listStudent").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/classes/listStudent/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/classes/add").access("hasAnyRole( 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/classes/add/").access("hasAnyRole( 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/classes/delete").access("hasAnyRole( 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/classes/delete/").access("hasAnyRole( 'ROLE_ADMIN', 'ROLE_TEACHER')");


        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/account/login")//
                .defaultSuccessUrl("/success?msg=true")//
                .failureUrl("/account/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/account/logout").logoutSuccessUrl("/account/logoutSuccessful").permitAll();

        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }


    public LogoutHandler logoutHandler() {
        return new LogoutHandlerImpl();
    }
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

}
