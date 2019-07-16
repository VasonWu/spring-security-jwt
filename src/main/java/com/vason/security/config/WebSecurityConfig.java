package com.vason.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // For H2-Console display
    http.headers().frameOptions().disable();

    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/css/**", "/index").permitAll()
        .antMatchers("/user/**").hasRole("USER")
        .anyRequest().authenticated()
        .and().formLogin()
        .and().logout().permitAll();
  }

  /**
   * Configure User info through JDBC
   *
   * @param auth
   * @throws Exception
   */
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled from user where username=?")
        .authoritiesByUsernameQuery(
            "select user.username as username, user_role.role from user inner join user_role on user.id = user_role.user_id where user.username=?")
        .rolePrefix("ROLE_")
        .passwordEncoder(NoOpPasswordEncoder.getInstance());
  }

//  /**
//   * Configure User Info in memory
//   * @param auth
//   * @throws Exception
//   */
//  @Override
//  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//        .inMemoryAuthentication()
//        // {noop} is necessary for non-secure password.
//        .withUser("user").password("{noop}password").roles("USER");
//  }
}
