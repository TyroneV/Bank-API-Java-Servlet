package com.banking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestHandler {
    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] uri = request.getRequestURI().split("/");
        if(uri.length > 2) {
            if (uri[2].equals("api") && uri.length > 3) {
                switch (uri[3]) {
                    case "login":
                        LoginController.login(request, response);
                        break;
                    case "logout":
                        LoginController.logout(request, response);
                        break;
                    case "register":
                        RegisterController.register(request, response);
                        break;
                    case "register-user":
                        RegisterController.submit(request, response);
                        break;
                    case "css":
                        break;
                    case "createAccount"://Create account page
                        HomeController.postAccountCreatePage(request, response);
                        break;
                    case "create-account"://creates
                        HomeController.submitAccount(request, response);
                        break;
                    case "accounts":
                        if (uri.length > 4) {
                            if (uri[4].equals("details")) {
                                AccountController.getAccountPage(request, response);
                                break;
                            } else if (uri[4].equals("transfer")) {
                                if (uri.length > 5) {
                                    if (uri[5].equals("submit")) {
                                        AccountController.submitTransfer(request, response);
                                        break;
                                    }
                                    break;
                                }
                                AccountController.transfer(request, response);
                                break;
                            } else if (uri[4].equals("withdraw")) {
                                if (uri.length > 5) {
                                    if (uri[5].equals("submit")) {
                                        AccountController.submitWithdraw(request, response);
                                        break;
                                    }
                                    break;
                                }
                                AccountController.withdraw(request, response);
                                break;
                            } else if (uri[4].equals("deposit")) {
                                if (uri.length > 5) {
                                    if (uri[5].equals("submit")) {
                                        AccountController.submitDeposit(request, response);
                                        break;
                                    }
                                    break;
                                }
                                AccountController.deposit(request, response);
                                break;
                            }
                        } else {
                            HomeController.getHomePage(request, response);
                            break;
                        }
                    case "update":
                        if(uri.length > 4)
                        {
                            if(uri[4].equals("user")){
                                UserUpdateController.getUserUpdatePage(request,response);
                                break;
                            }else if(uri[4].equals("user-submit")){
                                UserUpdateController.sendUpdate(request,response, false);
                            }else if(uri[4].equals("user-admin")){
                                UserUpdateController.sendUpdate(request,response, true);
                            } else if(uri[4].equals("update-account")){
                                AccountUpdateController.submitAccountUpdate(request,response);
                            }
                        }else {
                            if(request.getParameter("update").equals("user")) {
                                UserUpdateController.getUserUpdateAdmin(request, response);
                            } else if(request.getParameter("update").equals("account")){
                                AccountUpdateController.getAccountUpdatePage(request,response);
                            }
                        }
                        break;
                    default:
                        HomeController.getHomePage(request, response);
                }
            }
        } else {
            HomeController.getHomePage(request, response);
        }
    }
}
