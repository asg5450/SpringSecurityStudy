package me.whiteship.demospringsecurityform.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public AccessDecisionManager accessDecisionManager(){
        //RoleHierachyImpl : Role 간에 포함자를 정의하는 Hierachy 세팅 시작점!
        //.serHierachy("포함자 관계")
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        //위에서 만든 Hierachy를 DefaultWebSecurityExpressionHandler에 세팅
        //.setRoleHierachy("위에서 정의한 RoleHierarchyImpl객체")
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        //위에서 만든 RoleHierachy를 WebExpressionVoter에 세팅
        //.setExpressionHandler("위에서 정의한 DefaultWebSecurityExpressionHandler객체")
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(handler);

        //위에서 만든 WebExpressionVoter를 담은 객체들을 List<AccessDecisionVoter<? extends Object>>에 List 매개변수로 생성
        List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(webExpressionVoter);

        //List<AccesssDecisionVoter<? extends Object>> 객체를 매개변수로 담는 new AffirmativeBased 반환
        return new AffirmativeBased(voters);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        //불필요한 요청들에 대한 filter 생성을 막아서 성능 이득!
        //.ignoring()은 방식이 많기 때문에 더 찾아서 사용하기를 바람
    }
    //http.authorizeRequests() : url로 들어오는 요청을 어떤 경우에 허락할지
    //.mvcMatchers("페이지 url1", "페이지 url2") : 괄호 안에 url요청에 권한 설정을 할 때
    //.anyRequest() : mvcMatchers()에 설정되지 않은 나머지 페이지 전부에 적용
    //.permitAll() : 전체 허용
    //.hasRole("권한자 대문자로") : 해당 Role 소유자만 해당 페이지 허용
    //.authenticated() : Role에 상관없이 로그인만 하면 가능
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/info", "/account/**").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                .mvcMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager());
        http.formLogin();   //기본 로그인, 로그아웃 html 제공, 로그아웃 기능
        http.httpBasic();
        //CSRF 토큰 인증 방식을 사용하지 않겠다는 설정
//        http.csrf().disable();

        //SecurityContextHolder.setStrategyName() : 기본값은 ThreadLocal로서 같은 쓰레드에만 공유함
        //.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
        // => 비동기로 처리 관련 내부에 생긴 다른 쓰레드에서도 ContextHolder를 공유
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
