package site.easy.to.build.crm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.login.LoginService;

@RestController
@RequestMapping("api/login")
public class LoginRestController {
    LoginService loginService;

    @Autowired
    public LoginRestController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @PostMapping
    public User login(@RequestParam("username") String username,@RequestParam("password") String password) {
        return loginService.checkLogin(username,password);
    }
}

