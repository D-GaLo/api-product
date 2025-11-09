package com.product.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {
	
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/error", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/info", "/actuator/health").permitAll()
				.requestMatchers(HttpMethod.GET, "/category").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.GET, "/category/active").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers(HttpMethod.POST, "/category").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/category/{id}").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/category/{id}/enable").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/category/{id}/disable").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.GET, "/product").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers(HttpMethod.GET, "/product/{id}").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers(HttpMethod.POST, "/product").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/product/{id}").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/product/{id}/enable").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/product/{id}/disable").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.GET, "/product/{id}/image").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers(HttpMethod.POST, "/product/{id}/image").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers(HttpMethod.DELETE, "/product/{id}/image/{product-image-id}").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers(
								    "/error",
								    "/swagger-ui/**",
								    "/swagger-ui.html",
								    "/v3/api-docs/**",
								    "/v3/api-docs",
								    "/swagger-resources/**",
								    "/webjars/**",
								    "/favicon.ico",
								    "/actuator/info",
								    "/actuator/health"
								).permitAll())
		.cors(cors -> cors.configurationSource(corsConfig))
		.httpBasic(Customizer.withDefaults())
		.formLogin(form -> form.disable())
		.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
}