package org.elis.eventsmanager.security;

import org.elis.eventsmanager.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.elis.eventsmanager.util.UtilPath.*;

@Configuration
@EnableWebSecurity
public class FilterChainManager {
    private final FilterJwt filterJwt;
    private final AuthenticationProvider authenticationProvider;

    public FilterChainManager(FilterJwt filterJwt, AuthenticationProvider authenticationProvider) {
        this.filterJwt = filterJwt;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
        MvcRequestMatcher.Builder builder = new MvcRequestMatcher.Builder(handlerMappingIntrospector);
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .headers(c->c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(builder.pattern("/admin/**")).hasRole(Role.ADMIN.toString())
                        .requestMatchers(builder.pattern("/customer/**")).hasRole(Role.CUSTOMER.toString())
                        /*.requestMatchers("/login").permitAll()
                        .requestMatchers("/all/**").permitAll()
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.toString())
                        .requestMatchers("/customer/**").hasRole(Role.CUSTOMER.toString())*/
                        .anyRequest().permitAll()
                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
