package me.whiteship.demospringsecurityform.form;

import me.whiteship.demospringsecurityform.account.AccountContext;
import me.whiteship.demospringsecurityform.account.AccountRepository;
import me.whiteship.demospringsecurityform.account.UserAccount;
import me.whiteship.demospringsecurityform.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {
    @Autowired SampleService sampleService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserAccount userAccount){
        if(userAccount == null){
            model.addAttribute("message", "Hello Index");
        }else{
            model.addAttribute("message", "Hello, " + userAccount.getUsername());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model){
        model.addAttribute("message", "Hello Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        model.addAttribute("message", "Hello dashboard, " + principal.getName());
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal){
        model.addAttribute("message", "Hello User, " + principal.getName());
        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    //asyn-Handler는 Callable<> 리턴
    public Callable<String> asyncHandler(){
        SecurityLogger.log("MVC");
        return () -> {
            SecurityLogger.log("Callable");
            return "Async Handler";
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService(){
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }

}
