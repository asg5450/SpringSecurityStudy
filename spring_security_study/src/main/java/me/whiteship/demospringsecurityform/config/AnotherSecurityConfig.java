package me.whiteship.demospringsecurityform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 15)  //여러 개의 Config가 있을 때, 우선순위를 표현
                                        //LOWEST보다 HIGH_PRECEDENCE가 더 우선순위가 높고
                                        //같은 LOWEST끼리는 '-음수'가 마이너스로 클수록 우선순위가 높음
public class AnotherSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/account/**")  //antMatcher()도 mvcMatcher와 동일
                        .authorizeRequests()
                                .anyRequest().permitAll();
    }
}
