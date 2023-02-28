package me.whiteship.demospringsecurityform.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    // UserDetailsService는 Spring Security에서 제공되는 Class
    // Spring Security에서 '데이터엑세스오브젝트'를 사용해서 DB에서 사용자 정보를 가져와서 인증을 할 때 사용

    @Autowired AccountRepository accountRepository;

    //username를 받아와서 해당하는 user정보를 DB에서 가져와서 UserDetails형으로 리턴해주는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Security에서 제공하는 UserDetails와 같은 필드를 가진 우리가 만든 Account Entity, Repository를 사용하여 DB에서 user 정보 찾기
        Account account = accountRepository.findByUsername(username);

        //username에 해당하는 user정보가 DB에 없을 때 "UsernameNotFoundException(username)" 예외 처리
        if(account == null){
            throw new UsernameNotFoundException(username);
        }

        //User라는 Security 제공 Class의 builder를 통해 UserDetails를 대신해서 리턴가능
        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }


    //Spring Security는 필수적으로 비밀번호 암호화가 필요하다. ex) {noop}0000... "노옵션", {sha256?}0000... "256자리 해쉬화"
    public Account createNew(Account account) {

        //@ModelAttribute로 받아온 Account객체로부터 넘어온 비밀번호 예) 123 을 {noop}123으로 변환
        account.setPassword("{noop}" + account.getPassword());

        //this는 해당 클래스의 필드, Autowired로 얻은 Bean이 있는 객체를 지칭
        return this.accountRepository.save(account);
    }
}