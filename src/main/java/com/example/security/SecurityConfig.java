package com.example.security;

import com.example.domain.Reader;
import com.example.jpa.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Overriding security config
 * to make it more customized.
 *
 * @author dkorolev
 *         Date: 11.04.2017
 *         Time: 19:24
 */
@Profile("production")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

     private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO find way to enable csrf
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/readingList").access("hasRole('READER')")
                .antMatchers("/readingList/").access("hasRole('READER')")
                .antMatchers("/").access("hasRole('READER')")
                .antMatchers("/**").permitAll()
                .and()
                    .formLogin()
                     .loginPage("/login")
                     .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username ->
            {
                Reader reader = new Reader();
                reader.setUsername(username);
                reader.setPassword("123");
                readerRepository.saveAndFlush(reader);
                UserDetails userDetails = readerRepository.findOne(username);
                if (userDetails != null) {
                    return userDetails;
                }
                throw new UsernameNotFoundException("User '" + username + "' not found.");
                 });
    }

    @Autowired
    public void setReaderRepository(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }
}
