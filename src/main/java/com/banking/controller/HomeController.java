package com.banking.controller;

import com.banking.dao.AccountDao;
import com.banking.dao.UserAccountDao;
import com.banking.dao.imp.AccountDaoImp;
import com.banking.dao.imp.UserAccountDaoImp;
import com.banking.model.Account;
import com.banking.model.AccountStatus;
import com.banking.model.AccountType;
import com.banking.model.User;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HomeController {

    public static void getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(session != null && session.getAttribute("current_user") != null){
            accountSummaryPage(request,response);
        }
    }

    public static void postAccountCreatePage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("current_user");
        if(session == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(session != null && user != null && request.getMethod().equals("POST")){
            createAccountPage(request,response,"");
        }
    }

    public static void submitAccount(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("current_user");
        if(session == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(session != null && user != null && request.getMethod().equals("POST")){
            Account account = new Account(new AccountStatus(1),
                    new AccountType(Integer.parseInt(request.getParameter("account_type"))));
            try{
                AccountDao accountDao = new AccountDaoImp();
                account = accountDao.submitAccount(account);
                UserAccountDao userAccountDao = new UserAccountDaoImp();
                session.setAttribute("current_user",userAccountDao.submitNewUserAccount(user.getUserId(),account.getAccountId()));
            } catch (Exception e){
                createAccountPage(request,response,"<h4 class=\"error\">Failed to login. Incorrect username or password.</h4>");
            }
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
        customInput += HtmlBuilder.createAccountButton();
        PrintWriter out = response.getWriter();
        out.write(HtmlBuilder.accountSummaryUpper(currentUser.getUsername(),"Welcome") + customInput + HtmlBuilder.accountSummaryLower());
    }

    private static void createAccountPage(HttpServletRequest request, HttpServletResponse response, String additionalInput) throws IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        String customInput = "";
        User currentUser = (User)session.getAttribute("current_user");
        customInput += HtmlBuilder.createAccountForm();
        customInput += additionalInput;
        PrintWriter out = response.getWriter();
        out.write(HtmlBuilder.accountSummaryUpper(currentUser.getFirstName() + " " + currentUser.getLastName(),"") + customInput + HtmlBuilder.accountSummaryLower());
    }
}
