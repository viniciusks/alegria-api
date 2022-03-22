package br.com.autadesouza.alegriaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTConfiguration extends WebSecurityConfigurerAdapter {

    private final ConfigUserDetailsService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguration(ConfigUserDetailsService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/oauth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/oauth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.POST, "/musicas").authenticated()
                .antMatchers(HttpMethod.PUT, "/musicas").authenticated()
                .antMatchers(HttpMethod.DELETE, "/musicas").authenticated()
                .antMatchers(HttpMethod.POST, "/autores").authenticated()
                .antMatchers(HttpMethod.PUT, "/autores").authenticated()
                .antMatchers(HttpMethod.POST, "/autores").authenticated()
                .antMatchers(HttpMethod.PUT, "/autores").authenticated()
                .and()
                .addFilter(new JWTAuthenticatorFilter(authenticationManager()))
                .addFilter(new JWTValidatorFilter(authenticationManager(), usuarioService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        org.springframework.web.cors.CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }
}
