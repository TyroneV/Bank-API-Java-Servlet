package com.banking.controller;

import com.banking.model.User;
import com.banking.service.BankingService;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterController {
    public static void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false)==null){
            registerPage(response,"");
        }else {
            response.sendRedirect("http://localhost:6969/rocp-bank/");
        }
    }
    public static void submit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user =
                new User(
                        request.getParameter("username"),
                        request.getParameter("password"),
                        request.getParameter("first_name"),
                        request.getParameter("last_name"),
                        request.getParameter("email"),
                        BankingService.findRole(Integer.parseInt(request.getParameter("user_type"))));
        if(BankingService.register(user) != null){
            registerPage(response,"<h4 class=\"success\">Account Created!</h4>");
        }else {
            registerPage(response,"<h4 class=\"error\">Username already exist! please try again!</h4>");
        }
    }
    private static void registerPage(HttpServletResponse response,String customInput) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(HtmlBuilder.registerUpperPortion() + customInput + HtmlBuilder.registerLowerPortion());
    }
}
