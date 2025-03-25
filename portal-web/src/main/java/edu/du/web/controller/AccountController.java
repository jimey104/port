package edu.du.web.controller;

import edu.du.web.dto.AccountDTO;
import edu.du.web.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final HttpSession httpSession;


    @GetMapping
    public String test(Model model) {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        model.addAttribute("account", accounts);
        return "account/test";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if(httpSession.getAttribute("account") != null) {
            return "redirect:/account";
        }
        model.addAttribute("account", new AccountDTO());
        return "account/login";
    }

    @PostMapping("/loginId")
    public String loginId(@ModelAttribute AccountDTO account) {
        if(accountService.loginAccount(account)){
            httpSession.setAttribute("account", account);
            return "redirect:/account";
        }
        return "account/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("account", new AccountDTO());
        return "account/signup";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute AccountDTO account) {
        accountService.saveAccount(account);
        return "redirect:/account";
    }
}
