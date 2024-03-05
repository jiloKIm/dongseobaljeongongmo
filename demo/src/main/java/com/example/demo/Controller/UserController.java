package com.example.demo.Controller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RequiredArgsConstructor
@Controller // @RestController를 제거하고 @Controller만 사용
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){

        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String fullname,@RequestParam String password){


        User user=userService.confirm(password);

        return "redirect:/user/use_function/" + user.getId(); //
    }

    @GetMapping("/create")
    public  String create(){

        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String fullname,
                         @RequestParam String email,
                         @RequestParam String password,
    @RequestParam String confirm_password){



        User user=userService.save(fullname,email,password);


        return "redirect:/user/" + user.getId(); //

    }
    @GetMapping("/user/{userId}")
    public  String interfaceview(@PathVariable("userId") Long userId,Model model){
        User user=userService.find(userId);
        model.addAttribute("fullName", user.getFullname());


        return "use_function";
    }













}
