package com.banking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RequestHandler {
    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] uri = request.getRequestURI().split("/");
            if (uri[2].equals("api") && uri.length > 3) {
                switch (uri[3]) {
                    case "login":
                        LoginController.login(request, response);
                        break;
                    case "logout":
                        LoginController.logout(request, response);
                        break;
                    case "register":
                        RegisterController.register(request,response);
                        break;
                    case "register-user":
                        RegisterController.submit(request,response);
                        break;
                    case "css":
                        break;
                    case "createAccount"://Create account page
                        HomeController.postAccountCreatePage(request,response);
                        break;
                    case "create-account"://creates
                        HomeController.submitAccount(request,response);
                        break;
                    case "accounts":
                        try {
                            if(uri[4].equals("details")){
                                AccountController.getAccountPage(request,response);
                            }else if(uri[4].equals("transfer")){

                            }else if(uri[4].equals("withdraw")){

                            }else if(uri[4].equals("deposit")){

                            }
                        }catch (IndexOutOfBoundsException e){
                            HomeController.getHomePage(request, response);
                        }
                        break;
                    default:
                        HomeController.getHomePage(request, response);
                }
            } else {
                HomeController.getHomePage(request, response);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            HomeController.getHomePage(request,response);
        }
    }
}
