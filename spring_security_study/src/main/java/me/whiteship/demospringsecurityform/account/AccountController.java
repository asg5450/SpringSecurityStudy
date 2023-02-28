package me.whiteship.demospringsecurityform.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired AccountService accountService;

    //임시 회원가입 로직 (url로 Account 객체의 필드를 넘겨줘서 회원가입이 됨  {noop}을 비밀번호에 붙여주는 service로직이 포함되어 있음)
    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account){
        return accountService.createNew(account);
    }

    //@ModelAttribute : @RequestParam의 진화형으로, 단일 정보 매핑이 아니라 객체의 필드값인 여러개의 정보를 한 번에 매핑해줌
}
