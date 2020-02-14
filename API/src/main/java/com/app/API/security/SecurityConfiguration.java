package com.app.API.security;

import com.app.API.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        auth
                .authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                        //   JWT
                        http
                            // Enable JWT config bypass
                            .csrf().disable()
                            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            .and()
                            // JWT filter
                            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                            .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                            // Authorization
                            .authorizeRequests()

                               /*                                    AUTHORIZATION                                         */

                                // Users
                                .antMatchers(HttpMethod.GET, "/api/public/admin/users").hasAnyAuthority("ACCESS_VIEWUSERS", "ROLE_ADMIN")
                                .antMatchers(HttpMethod.POST, "/api/public/admin/give").hasAnyAuthority("ACCESS_GIVEPERMISSION", "ROLE_ADMIN")

                                // Tickets
                                .antMatchers(HttpMethod.GET, "/api/public/admin/ticket").hasAnyAuthority("ROLE_ADMIN", "ACCESS_VIEWTICEKTS")

                                // General
                                .antMatchers("/register").permitAll()
                                .antMatchers("/api/public/admin/*").hasAuthority("ROLE_ADMIN")
                                .antMatchers("api/public/*").authenticated()
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
