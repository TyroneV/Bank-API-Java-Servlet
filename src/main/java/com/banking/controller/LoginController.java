package com.banking.controller;

import com.banking.model.User;
import com.banking.service.BankingService;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController {

    public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getMethod().equals("POST")) {
            if (request.getSession(false) == null) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                User user = BankingService.login(username, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("current_user", user);
                    response.sendRedirect("http://localhost:6969/rocp-bank/api/accounts");
                } else {
                    loginPage(response,"<h4 class=\"error\">Failed to login. Incorrect username or password.</h4>");
                }
            }
        }else{
            if(request.getSession(false)!=null) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/accounts");
            } else {
                loginPage(response,"");
            }
        }
    }
    public static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            response.setContentType("text/html");
        }
        HomeController.getHomePage(request, response);
    }

    private static void loginPage(HttpServletResponse response,String customInput) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write(HtmlBuilder.loginUpper() + customInput + HtmlBuilder.loginLower());
    }
}
