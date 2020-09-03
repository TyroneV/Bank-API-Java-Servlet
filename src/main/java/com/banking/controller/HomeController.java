package com.banking.controller;

import com.banking.model.Account;
import com.banking.model.AccountStatus;
import com.banking.model.AccountType;
import com.banking.model.User;
import com.banking.service.BankingService;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HomeController {

    public static void getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check && user.getRole().getRoleId() < 3) {
                EmployeeController.getEmployeePage(request, response);
            } else if (check) {
                accountSummaryPage(request, response);
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    public static void postAccountCreatePage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (check && request.getMethod().equals("POST")
                    && BankingService.listOfPendingAccounts(user.getUserId()).size() == 0) {
                createAccountPage(request, response, "");
            } else {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    public static void submitAccount(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (check && request.getMethod().equals("POST")
                    && BankingService.listOfPendingAccounts(user.getUserId()).size() == 0) {
                Account account = new Account(new AccountStatus(1),
                        new AccountType(Integer.parseInt(request.getParameter("account_type"))));
                if (BankingService.submitAccount(account, user) != null) {
                    createAccountPage(request, response, "<h4 class=\"success\">Your account is now pending please await for further approval!</h4>");
                    user = BankingService.findUser(user.getUserId());
                    session.setAttribute("current_user", user);
                } else {
                    createAccountPage(request, response, "<h4 class=\"error\">Failed to create account.</h4>");
                }
            } else {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    private static void accountSummaryPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        String customInput = "";
        User currentUser = (User)session.getAttribute("current_user");
        for (Account a: currentUser.getAccounts()) {
            customInput += HtmlBuilder.accountFormBuilder(a);
        }
        if(BankingService.listOfPendingAccounts(currentUser.getUserId()).size() == 0) {
            customInput += HtmlBuilder.createAccountButton();
        }
        PrintWriter out = response.getWriter();
        out.write(HtmlBuilder.accountSummaryUpper(currentUser.getFirstName() + " " + currentUser.getLastName(),"Welcome") + customInput + HtmlBuilder.accountSummaryLower());
    }

    private static void createAccountPage(HttpServletRequest request, HttpServletResponse response, String additionalInput) throws IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        String customInput = "";
        User currentUser = (User)session.getAttribute("current_user");
        customInput += HtmlBuilder.createAccountForm(additionalInput);
        PrintWriter out = response.getWriter();
        out.write(HtmlBuilder.accountSummaryUpper(currentUser.getFirstName() + " " + currentUser.getLastName(),"") + customInput + HtmlBuilder.accountSummaryLower());
    }
}
