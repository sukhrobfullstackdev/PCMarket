package uz.pcmarket.pcmarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
// @Configuration - bu boshqa annotatsiyalarga o'xshab bean qilib beradi va shu bilan birga buni ichida bean yaratsa bo'ladi.
@EnableWebSecurity // buni ham qo'shishimiz kerak.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
////                .antMatchers(HttpMethod.GET, "/api/product/*").hasRole("ADMIN")
////                .antMatchers("/api/product/**").hasRole("DIRECTOR")
////                .antMatchers(HttpMethod.GET, "/api/product/*").hasAuthority("READ_PRODUCT")
////                .antMatchers("/api/proAnvarbek Turdaliyev17:43
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("super_admin").password(passwordEncoder().encode("super_admin")).roles("SUPER_ADMIN").authorities("ADD_PRODUCT", "EDIT_PRODUCT", "READ_ALL_PRODUCT", "READ_PRODUCT", "DELETE_PRODUCT")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("moderator")).roles("MODERATOR").authorities("ADD_PRODUCT", "EDIT_PRODUCT", "READ_ALL_PRODUCT", "READ_PRODUCT")
                .and()
                .withUser("operator").password(passwordEncoder().encode("operator")).roles("OPERATOR").authorities("ADD_PRODUCT", "READ_ALL_PRODUCT", "READ_PRODUCT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/product/*").hasRole("ADMIN")
//                .antMatchers("/api/product/**").hasRole("DIRECTOR")
//                .antMatchers(HttpMethod.GET, "/api/product/*").hasAuthority("READ_PRODUCT")
//                .antMatchers("/api/product/**").hasAnyAuthority("ADD_PRODUCT","READ_ALL_PRO
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
