package me.whiteship.demospringsecurityform.form;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {
    public void dashboard(){
        // Security ContextHolder > Security Context >  Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authentication > Principal
        Object principal = authentication.getPrincipal();

        // 권한을 확인할 수 있는 GrantedAuthority 객체    * 특징 : ROLL_ 를  알아서 붙여서 "USER"를 입력해도 "ROLL_USER"로 저장한다.
        //Authentication객체.getAuthorities()는 Pricipal의 권한만 가지고 있는 정보
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();
    }
}
