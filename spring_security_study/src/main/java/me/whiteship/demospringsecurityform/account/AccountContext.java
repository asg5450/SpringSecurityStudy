package me.whiteship.demospringsecurityform.account;

public class AccountContext {

    //Account형의 데이터가 담길 수 있는 ThreadLocal객체를 생성 (new)
    private static final ThreadLocal<Account> ACCOUNT_THREAD_LOCAL = new ThreadLocal<>();

    //account형을 ThreadLocal에 set메소드
    public static void setAccount(Account account){
        ACCOUNT_THREAD_LOCAL.set(account);
    }

    //담았던 Account형 정보를 받아오는 get메소드
    public static Account getAccount(){
        return ACCOUNT_THREAD_LOCAL.get();
    }

}
