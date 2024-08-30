package com.techlabs.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.techlabs.security.JwtAuthenticationEntryPoint;
import com.techlabs.security.JwtAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
//@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    private final JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint,
                          JwtAuthenticationFilter authenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000"); // Adjust this as needed for your frontend URL
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        // Apply CORS configuration to the source
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        // Add the CORS filter
        http.addFilterBefore(new CorsFilter(corsConfigurationSource), ChannelProcessingFilter.class);


        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/users/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/contacts/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.GET, "/api/contacts/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.DELETE, "/api/contacts/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.PUT, "/api/contacts/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.PATCH, "/api/contacts/**").hasRole("STAFF")

                                .requestMatchers(HttpMethod.POST, "/api/contacts-details/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.GET, "/api/contacts-details/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.DELETE, "/api/contacts-details/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.PUT, "/api/contacts-details/**").hasRole("STAFF")
                                .requestMatchers(HttpMethod.PATCH, "/api/contacts-details/**").hasRole("STAFF")

                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/**","/v3/api-docs").permitAll()
                                .anyRequest().permitAll()

                ).exceptionHandling( exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                ).sessionManagement( session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }


}
