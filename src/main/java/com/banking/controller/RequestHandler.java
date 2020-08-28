package com.banking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestHandler {
    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] uri = request.getRequestURI().split("/");
        if (uri[2].equals("api") && uri.length > 3) {
            switch (uri[3]) {
                case "login":
                    LoginController.login(request, response);
                    break;
                case "logout":
                    LoginController.logout(request, response);
                    break;
                default:
                    HomeController.getHomePage(request, response);
            }
        } else {
            HomeController.getHomePage(request, response);
        }
    }
}
