package project3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import project3.filter.JwtAuthenticationFilter;
import project3.security.JwtTokenUtil;
import project3.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private UserDetailsServiceImpl userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtTokenUtil jwtTokenUtil){
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //** means all endpoints with this prefix have the rule
                        //define global path restriction
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/registration/**").hasRole("ADMIN")
                        .requestMatchers("/student/**").hasAnyRole("USER", "ADMIN")
                        //.requestMatchers(HttpMethod.DELETE, "/student/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()  //other request only need to check authentication
                )
                .addFilterBefore(getJwtAuthenticationFilter(), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsServiceImpl userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtTokenUtil, userDetailsService);
    }
}