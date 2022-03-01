package com.example.xujing.brush_questions.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
    /**
     * 登录页面
     *
     */
    @RequestMapping("login")
   public String login(){

       return "register.htm";
   }

}
