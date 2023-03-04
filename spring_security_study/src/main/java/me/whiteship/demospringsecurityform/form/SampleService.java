package me.whiteship.demospringsecurityform.form;

import me.whiteship.demospringsecurityform.account.Account;
import me.whiteship.demospringsecurityform.account.AccountContext;
import me.whiteship.demospringsecurityform.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {
    public void dashboard(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("================");
        System.out.println(userDetails.getUsername());
    }

    @Async  //메소드를 비동기화시키는 어노테이션 + 사용하려면 Application Class에 @EnableAsync도 붙여줘야 함
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async Service is called.");
    }
}
