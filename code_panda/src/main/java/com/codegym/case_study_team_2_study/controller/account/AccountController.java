package com.codegym.case_study_team_2_study.controller.account;

import com.codegym.case_study_team_2_study.dto.account.AccountDto;
import com.codegym.case_study_team_2_study.dto.account.IAccountDto;
import com.codegym.case_study_team_2_study.model.account.Account;
import com.codegym.case_study_team_2_study.service.account.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    IAccountService accountService;

    @GetMapping("")
    public String showAccount(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "") String keyword, Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("email").ascending());
        Page<IAccountDto> accountDtoPage = accountService.searchByEmail(pageable, keyword);
        model.addAttribute("accounts", accountDtoPage);
        model.addAttribute("keyword", keyword);
        return "account/list";
    }

    @GetMapping("/login")
    public String showLogin(Model model, @RequestParam(name = "account", required = false) Account account) {
        model.addAttribute("account", account);
        return "account/login";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int deleteId) {
        accountService.deleteAccount(deleteId);
        return "redirect:/account";
    }

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("account", new Account());
        return "account/signup";
    }

    @PostMapping("/create")
    public String signup(@ModelAttribute Account account, @RequestParam String repeat, RedirectAttributes redirect, Model model) {
        Account newAccount = accountService.findByEmail(account.getEmail());
        if (newAccount != null) {
            model.addAttribute("message", "Email đã được đăng ký!");
            return "account/signup";
        } else if (account.getPassword().equals(repeat)) {
            account.setStatus(true);
            accountService.addAccount(account);
            redirect.addFlashAttribute("message", "Đăng ký thành công!");
            return "redirect:/account/login";
        }
        model.addAttribute("message", " Xác thực mật khẩu không hợp lệ!");
        return "account/signup";
    }


    @GetMapping("/logoutSuccessful")
    public String logoutPage(Model model) {
        return "/home";
    }

    @GetMapping("/change-password")
    public String showFormChange() {
        return "account/change_password";
    }

    @PostMapping("change-password")
    public String changePassword(Model model, Authentication authentication, @RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String repeatPassword, RedirectAttributes redirect) {
        String email = authentication.getName();
        Account account = accountService.findByEmail(email);
        if (!accountService.testPass(email, currentPassword)) {
            model.addAttribute("message", " Mật khẩu cũ không đúng!");
            return "account/change_password";
        } else if (!Objects.equals(newPassword, repeatPassword)) {
            model.addAttribute("message", " Xác nhận mật khẩu không đúng!");
            return "account/change_password";
        }

        accountService.changePass(email, newPassword);
        model.addAttribute("account", account);
        redirect.addFlashAttribute("message", "Đổi mật khẩu thành công!");
        return "redirect:/success";
    }

    @GetMapping("/forgot")
    public String showForgot(Model model) {
        return "account/forgotPassword";
    }

    @PostMapping("/forgot")
    public String change(@RequestParam String email, Model model) {
        Account account = accountService.findByEmail(email);
        if (account != null) {
            String code = accountService.sendEmailAndReturnCode(email);
            model.addAttribute("code", code);
            model.addAttribute("email", email);
            model.addAttribute("message", "Gửi thành công, hãy kiểm tra email!");
            return "/account/confirm_password";
        } else
            model.addAttribute("message", "Tài khoản không tồn tại!");
        return "account/forgotPassword";
    }

    @GetMapping("/confirm")
    public String showFormConfirm() {
        return "/account/confirm_password";
    }

    @PostMapping("/confirm-code")
    public String showConfirm(Model model, @RequestParam("code") String code,
                              @RequestParam("newPassword") String newPassword,
                              @RequestParam("rePassword") String rePassword,
                              @RequestParam("codeEmail") String codeEmail,
                              @RequestParam("email") String email, RedirectAttributes redirect) {
        if (newPassword.equals(rePassword)) {
            if (code.equals(codeEmail)) {
                Account account = accountService.findByEmail(email);
                account.setPassword(newPassword);
                accountService.forgotPassword(account);
                model.addAttribute("message", "Đổi mật khẩu thành công, vui lòng đăng nhập!");
                return "/account/login";
            } else {
                redirect.addFlashAttribute("newPassword", newPassword);
                redirect.addFlashAttribute("rePassword", rePassword);
                redirect.addFlashAttribute("codeEmail", codeEmail);
                redirect.addFlashAttribute("code", code);
                redirect.addFlashAttribute("message", "Vui lòng kiểm tra lại mã!");
                return "redirect:/account/confirm";
            }
        } else {
            redirect.addFlashAttribute("newPassword", newPassword);
            redirect.addFlashAttribute("rePassword", rePassword);
            redirect.addFlashAttribute("codeEmail", codeEmail);
            redirect.addFlashAttribute("code", code);
            redirect.addFlashAttribute("message", "Kiểm tra lại mật khẩu mới!");
            return "redirect:/account/confirm";
        }
    }

    @GetMapping("/add")
    public String showFormAddAccount(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "/account/add";
    }

    @PostMapping("/add")
    public String addAccount(@Validated @ModelAttribute AccountDto accountDto, BindingResult bindingResult,
                             RedirectAttributes redirect, @RequestParam("repassword") String repeatPass, @RequestParam("role") int role) {
        new AccountDto().validate(accountDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/account/add";
        }

        if (accountService.findByEmail(accountDto.getEmail()) != null) {
            redirect.addFlashAttribute("message", " Email đã được đăng ký!");
            return "redirect:/account/add";
        }
        if (!accountDto.getPassword().equals(repeatPass)) {
            redirect.addFlashAttribute("message", " Xác thực mật khẩu không hợp lệ!");
            return "redirect:/account/add";
        }
        Account newAccount = new Account();
        BeanUtils.copyProperties(accountDto, newAccount);
        accountService.createAccount(newAccount, role);
        redirect.addFlashAttribute("message", " Thêm mới thành công!");
        return "redirect:/account";
    }
}
