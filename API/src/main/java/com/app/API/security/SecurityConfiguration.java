package com.app.API.security;

import com.app.API.user.UserRepository;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.context.request.WebRequest;
import java.util.Map;

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
                                .antMatchers("/api/public/*").authenticated()
                                .antMatchers("/checkToken").authenticated()
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

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest requestAttributes, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                Throwable error = getError(requestAttributes);
                // of course you can customize any exception ( e.g : bad requests )
                if(error instanceof AccessDeniedException){
                    errorAttributes.clear();
                    errorAttributes.put("error", "unauthorized");
                    errorAttributes.put("error_description", "Full authentication is required to access this resource");
                }
                return errorAttributes;
            }
        };
    }


}
