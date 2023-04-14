package com.example.lab8.controller;

import com.example.lab8.dto.UserDto;
import com.example.lab8.entity.User;
import com.example.lab8.service.UserActionsService;
import com.example.lab8.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SecurityController {

    private UserService userService;

    private final UserActionsService userActionsService;

    public SecurityController(UserService userService, UserActionsService userActionsService) { this.userService = userService;
        this.userActionsService = userActionsService;
    }

    @GetMapping("/index")
    public String home() {
        userActionsService.info("Пользователь зашел на главную страницу");
        return "index";}

    @GetMapping("/login")
    public String login() {
        userActionsService.info("Пользователь зашел на страницу входа");
        return "login";}

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        userActionsService.info("Пользователь зашел на страницу регистрации");
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "На этот адрес электронной почты уже зарегестрированна учетная запись");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users =userService.findAllUsers();
        model.addAttribute("users", users);
        userActionsService.info("Пользователь зашел на страницу со всеми пользователями");
        return "users";
    }
}

