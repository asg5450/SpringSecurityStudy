package me.whiteship.demospringsecurityform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests() : url로 들어오는 요청을 어떤 경우에 허락할지
        //.mvcMatchers("페이지 url1", "페이지 url2") : 괄호 안에 url요청에 권한 설정을 할 때
        //.anyRequest() : mvcMatchers()에 설정되지 않은 나머지 페이지 전부에 적용
        //.permitAll() : 전체 허용
        //.hasRole("권한자 대문자로") : 해당 Role 소유자만 해당 페이지 허용
        //.authenticated() : Role에 상관없이 로그인만 하면 가능
        http.authorizeRequests()
                .mvcMatchers("/", "info").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.formLogin();   //기본 로그인, 로그아웃 html 제공, 로그아웃 기능
        http.httpBasic();

    }
}
