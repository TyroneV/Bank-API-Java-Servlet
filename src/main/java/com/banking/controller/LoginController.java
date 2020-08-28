package com.banking.controller;

import com.banking.model.User;
import com.banking.service.BankRpc;
import com.banking.service.BankingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController {

    public static void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getMethod().equals("POST")) {
            if (request.getSession(false) == null) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                BankRpc bankRpc = new BankingService();
                User user = bankRpc.login(username, password);
                System.out.println(username + password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("current_user", user);
                    System.out.println("IN ACCOUNT");
                    response.sendRedirect("http://localhost:6969/rocp-bank/api/accountSummary");
                } else {
                    request.getRequestDispatcher("/loginFailed.html").forward(request, response);
                }
            }
        }else{
            if(request.getSession(false)!=null) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/accountSummary");
            } else {
                request.getRequestDispatcher("/login.html").forward(request,response);
            }
        }
    }
    public static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(request.getSession(false)==null){
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            }else {
                request.getSession(false).invalidate();
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            }
    }
}
