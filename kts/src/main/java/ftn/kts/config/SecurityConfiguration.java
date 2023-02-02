package ftn.kts.config;


import ftn.kts.security.AuthTokenUtils;
import ftn.kts.security.auth.AuthEntryPoint;
import ftn.kts.security.auth.TokenAuthenticationFilter;
import ftn.kts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private AuthTokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationManager authManager(UserService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeRequests(auth -> {
                    auth.antMatchers("/api/unregisteredUsers/**").permitAll();
                    auth.antMatchers("/api/**").authenticated();

                })
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userService), BasicAuthenticationFilter.class)
                .build()
                ;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
        return (web) -> {
            web.ignoring().antMatchers(HttpMethod.POST, "/api/users/login", "/h2-console/**");
            web.ignoring().antMatchers(HttpMethod.GET, "/h2-console/**", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                    "/**/*.css", "/**/*.js");
        };

    }

}
