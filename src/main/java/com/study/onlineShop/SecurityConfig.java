package com.study.onlineShop;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.context.annotation.Bean;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // antMatchers是帮我们设置不同页面的权限的。
    http
            .csrf().disable() // 跨站点请求伪造（Cross-site request forgery）
            .formLogin() // 表单登录
            .loginPage("/login"); // 指明login的页面（如果没写会框架给一个）
    http
            .authorizeRequests() // 指明权限，*是任意字符；**是可以匹配/a/b这种多个level的。
            .antMatchers("/cart/**").hasAuthority("ROLE_USER")
            .antMatchers("/get*/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
            .antMatchers("/admin*/**").hasAuthority("ROLE_ADMIN")
            .anyRequest().permitAll();
    // 而且权限不够的时候，会自动redirect to login

    http   // logout不用写，用默认的就好了。
            .logout()
            .logoutUrl("/logout");

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth. // 这是一个测试用的账号。
            inMemoryAuthentication().withUser("mayaowei.study@gmail.com")
            .password("123").authorities("ROLE_ADMIN");

    auth
            .jdbcAuthentication()
            .dataSource(dataSource) //上面autowired进来
            .usersByUsernameQuery("SELECT emailId, password, enabled FROM users WHERE emailId=?")
            .authoritiesByUsernameQuery("SELECT emailId, authorities FROM authorities WHERE emailId=?");
// 这两行只能是raw SQL，留下问号”?”可以让框架帮我们输入用户的输入值。

  }

  @SuppressWarnings("deprecation") // spring5必须要加密，我们没有encode。所以直接返回就行了。
  @Bean
  public static NoOpPasswordEncoder passwordEncoder() {
    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); // 所以直接返回就行了。
  }
}

