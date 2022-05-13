package com.frontend.ui.configs;

import com.frontend.ui.model.Account;
import com.frontend.ui.services.AccountService;
import com.frontend.ui.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.Optional;

/**
 * Created by Default on 26/04/2022.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/global/**","/static/**").permitAll()
                .antMatchers("/hello","/api/login","/api/show").permitAll()
                .antMatchers("/api/login","/api/login2").permitAll()
                .antMatchers("/home").hasRole("USER")
                .anyRequest().authenticated()

                .and()
                .cors()
                .and()
                /*.exceptionHandling().and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()*/
                .exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationEntryPoint()).and()

                    .formLogin()
                    .loginPage("/login")
                        .loginProcessingUrl("/api/login")
                    .defaultSuccessUrl("/home")
                //.failureUrl("/login?error")
                    .permitAll()
                        .and()
                        .logout()
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true)
                        .permitAll();


        //http.authorizeRequests().antMatchers("/").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // old codes , hard coded authentication user
    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {

        Account res = accountService.findByUsername("benkuramax");

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username(res.getUsername_())
                        .password(res.getPassword_())
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

}

// GUIDE ONLY
        /*http
                // Start request permission configuration
                .authorizeRequests()
                // We specify a pattern in which any user can access multiple URL s.
                // Any user can access URL s starting with "/ resources/","/signup", or "/ about".
//      .antMatchers("/global*//**","/static*//**").permitAll()
                // Request matching / admin / * * only users with role admin role can access
                .antMatchers("/admin*//**").hasRole("ADMIN")
                // Request matching / user / * * role users with role admin and role user can access
                .antMatchers("/user*//**").hasAnyRole("ADMIN", "USER")
                // Any URL starting with '/ db /' needs to be accessible by users with both 'role admin' and 'role DBA' permissions.
                // As above, our hasRole method does not use the prefix "ROLE".
                // .antMatchers("/db*//**").access("hasRole('ADMIN') and hasRole('DBA')")
                // All other requests need to be authenticated before they can be accessed
                .anyRequest().authenticated().and().formLogin()
                // Login interface; default interface after successful login (no effect); default interface after unsuccessful login; form submission address
                .loginPage("/login").defaultSuccessUrl("/index.html").failureUrl("/login?error=true")
                // Default user name key value, default password key value
                .usernameParameter("username").passwordParameter("password").permitAll().and().rememberMe()
                .tokenValiditySeconds(1209600).key("rememberme");
//        .and()
//        .logout().logoutUrl("").logoutSuccessUrl("/index.html").permitAll();*/