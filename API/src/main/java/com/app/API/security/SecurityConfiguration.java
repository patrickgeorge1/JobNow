package com.app.API.security;

import com.app.API.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;
    private UserRepository userRepository;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserRepository userRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin")).authorities("ROLE_ADMIN", "ACCESS_TICKET")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("user")).authorities("ROLE_USER")
//                .and()
//                .withUser("ticket").password(passwordEncoder().encode("ticket")).authorities("ROLE_USER", "ACCESS_TICKET");

        auth
                .authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                        //   Basic Auth
        //            http
//                    .authorizeRequests()
//                        .antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
//                        .antMatchers("/api/public/ticket/view").hasAnyAuthority("ACCESS_TICKET", "ROLE_ADMIN")
//                        .antMatchers("/api/public/**").authenticated()
//                        .and()
//
//                    .httpBasic();
                        http
                            .csrf().disable()
                            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            .and()
                            // JWT filter
                            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                            .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                            .authorizeRequests()
                                .antMatchers("/show").permitAll()
                                .antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                                .antMatchers("/api/public/ticket/view").hasAnyAuthority("ACCESS_TICKET", "ROLE_ADMIN")
                                .antMatchers("/api/public/**").authenticated()
                        ;
        }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

}
