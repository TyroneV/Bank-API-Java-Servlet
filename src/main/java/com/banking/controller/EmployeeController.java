package com.banking.controller;

import com.banking.model.Account;
import com.banking.model.User;
import com.banking.service.BankingService;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

    public static void getEmployeePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check && user.getRole().getRoleId() < 3) {
                createEmployeePage(request,response);
            }else {
                response.sendError(403);
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    private static void createEmployeePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession(false).getAttribute("current_user");
        List<User> userList = new ArrayList<>();
        List<Account> accountList = new ArrayList<>();
        boolean admin;
        if(user.getRole().getRoleId() == 1){
            admin = true;
        }else {
            admin = false;
        }
        if(request.getParameter("find") != null){
            String searchType = request.getParameter("find");
            String findId = request.getParameter("find_id");
            switch (searchType){
                case "findUsers":
                    userList = BankingService.findUsers();
                    break;
                case "findUserId":
                    if(!findId.equals("")){
                        User u = BankingService.findUser(Integer.parseInt(findId));
                        if(u != null) {
                            userList.add(u);
                            break;
                        }
                        break;
                    }
                    break;
                case "findAccount":
                    accountList = BankingService.findAccounts();
                case "findAccountId":
                    if(!findId.equals("")){
                        Account a = BankingService.findAccount(Integer.parseInt(findId));
                        if(a != null){
                            accountList.add(a);

                            break;
                        }
                        break;
                    }
                    break;
                case "findAccountsByStatusId":
                    if(!findId.equals("")){
                        accountList = BankingService.findAccountsByStatusId(Integer.parseInt(findId));
                        break;
                    }
                case "findAccountsUserId":
                    if(!findId.equals("")){
                        accountList = BankingService.findAccountsByUserId(Integer.parseInt(findId));
                        break;
                    }
                default:
                    break;
            }
        }
        String html = HtmlBuilder.employeePage(userList,accountList,admin,user);
        PrintWriter out = response.getWriter();
        out.write(html);
    }
}
