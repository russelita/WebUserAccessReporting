package com.ujian.main.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
     
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailServices();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/ujian/laporan/add").access("hasRole('ROLE_USER')")
        .antMatchers("/ujian/dashboard/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    	.antMatchers("/ujian/laporan/view").access("hasRole('ROLE_ADMIN')").anyRequest().permitAll()
            .and()
            .formLogin()
                .usernameParameter("username")
                .defaultSuccessUrl("/ujian/dashboard/view")
                .permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    	
//    	http.authorizeRequests()
//    	.antMatchers("/ujian/laporan/add").access("hasRole('ROLE_USER')")
//    	.antMatchers("/ujian/laporan/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").anyRequest().permitAll()
//    	.and()
//    	.formLogin().loginPage("/login")
//    	.defaultSuccessUrl("/laporan/view").permitAll()
//    	.and()
//    	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    	
    	
    	
//    	.logout(logout -> logout
//    			.logoutUrl("/logout")
//    			.addLogoutHandler(new SecurityContextLogoutHandler())
//    			);
    	
    	
    	
    }
 

}
