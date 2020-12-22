package pl.kocjan.automatizer.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.configuration.security.jwt.JwtAuthenticationFilter;
import pl.kocjan.automatizer.configuration.security.jwt.JwtAuthorizationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/user/create").permitAll()
	                .antMatchers("/user/").hasRole("ADMIN")
	                .antMatchers("/user/activate").hasRole("ADMIN")
	                .antMatchers("/playbook/**").hasRole("VERIFIED")
	                .antMatchers("/host/**").hasRole("VERIFIED")
	                .and()
	                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
	                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl(passwordEncoder());
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	        return source;
	    }

}
