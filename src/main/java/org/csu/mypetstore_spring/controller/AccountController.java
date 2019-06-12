package org.csu.mypetstore_spring.controller;

import org.csu.mypetstore_spring.domain.Account;
import org.csu.mypetstore_spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;


    @GetMapping("/account/toSignOn")
    public String toSignOn(){
        return "account/SignOnForm";
    }

    @PostMapping("/account/SignOn")
    public String signOn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpSession session){
        Account account = accountService.getAccount(username,password);
        if (account == null) {
            String value = "Invalid username or password.  Signon failed.";
            session.setAttribute("message",value);
            return "account/SignOnForm";
        }
        else {
            session.setAttribute("account",account);
            return "catalog/main";
        }
    }

    @GetMapping("/account/toRegister")
    public String toRegister(){
        return "account/NewAccountForm";
    }

    @PostMapping("/account/register")
    public String register(Account account, HttpSession session){
        session.setAttribute("account",account);
        accountService.insertAccount(account);
        return "catalog/main";
    }

    @GetMapping("/account/toMyAccount")
    public String toMyAccount(){
        return "account/EditAccountForm";
    }

    @PostMapping("/account/editAccount")
    public String editAccount(Account account,HttpSession session){
        session.setAttribute("account",account);
        accountService.updateAccount(account);
        return "account/EditAccountForm";
    }

    @GetMapping("/account/signOut")
    public String signOut(HttpSession session){
        session.setAttribute("account",null);
        return "catalog/main";
    }
}
