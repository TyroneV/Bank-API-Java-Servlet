package com.banking.controller;

import com.banking.model.Account;
import com.banking.model.TransactionDetails;
import com.banking.model.User;
import com.banking.service.BankingService;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AccountController {

    public static void getAccountPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check && user.getRole().getRoleId() < 3) {
                EmployeeController.getEmployeePage(request, response);
            } else if (check) {
                accountPageBuilder(request, response, (User) session.getAttribute("current_user"));
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }
    public static void withdraw(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check && user.getRole().getRoleId() < 3) {
                EmployeeController.getEmployeePage(request, response);
            } else if (check) {
                withdrawPage(request, response, (User) session.getAttribute("current_user"), "");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    public static void submitWithdraw(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("current_user");
        if(session == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(session != null && user != null && request.getMethod().equals("POST")){
            Account account = BankingService.withdraw(user.getCurrentAccount().getAccountId(),
                    Double.parseDouble(request.getParameter("money")));
            if(account != null){
                user = BankingService.findUser(user.getUserId());
                user.setCurrentAccount(account);
                withdrawPage(request,response,user,"<h4 class=\"error\">Withdraw successful.</h4>");
            }
            else {
                withdrawPage(request,response,user,"<h4 class=\"error\">Withdraw failed please try again.</h4>");
            }
        }
    }
    public static void deposit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check && user.getRole().getRoleId() < 3) {
                EmployeeController.getEmployeePage(request, response);
            } else if (check) {
                depositPage(request, response, (User) session.getAttribute("current_user"), "");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    public static void submitDeposit(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("current_user");
        if(session == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(session != null && user != null && request.getMethod().equals("POST")){
            Account account = BankingService.deposit(user.getCurrentAccount().getAccountId(),
                    Double.parseDouble(request.getParameter("money")));
            if(account != null){
                user = BankingService.findUser(user.getUserId());
                user.setCurrentAccount(account);
                depositPage(request,response,user,"<h4 class=\"error\">Deposit successful.</h4>");
            }
            else {
                depositPage(request,response,user,"<h4 class=\"error\">Deposit failed please try again.</h4>");
            }
        }
    }

    public static void transfer(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check && user.getRole().getRoleId() < 3) {
                EmployeeController.getEmployeePage(request, response);
            } else if (check) {
                transferPage(request, response, (User) session.getAttribute("current_user"), "");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    public static void submitTransfer(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("current_user");
        if(session == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(session != null && user != null && request.getMethod().equals("POST")){
            Account account = BankingService.transfer(user.getCurrentAccount().getAccountId(),
                    Integer.parseInt(request.getParameter("target_account")),
                    Double.parseDouble(request.getParameter("money")));
            if(account != null){
                user = BankingService.findUser(user.getUserId());
                user.setCurrentAccount(account);
                transferPage(request,response,user,"<h4 class=\"error\">Transfer successful.</h4>");
            }
            else {
                transferPage(request,response,user,"<h4 class=\"error\">Unable to transfer.</h4>");
            }
        }
    }
    private static void accountPageBuilder(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        try {
            int accountId = Integer.parseInt(request.getParameter("account_id"));
            Account account =  BankingService.getAccountWithUsers(accountId);
            List<TransactionDetails> transactionDetails
                    = BankingService.getTransactionDetailsByAccountId(accountId);
            PrintWriter out = response.getWriter();
            user.setCurrentAccount(account);
            String html = HtmlBuilder.accountSummaryUpper(user.getFirstName() + " " + user.getLastName(), "Account Number : "+ account.getAccountId()) +
                    HtmlBuilder.accountTransactions(account,transactionDetails) +
                    HtmlBuilder.accountSummaryLower();
            out.write(html);
        } catch (Exception e){
            e.printStackTrace();
            response.sendError(404);
        }
    }

    private static void withdrawPage(HttpServletRequest request, HttpServletResponse response, User user,String results) throws IOException {
        Account account =  user.getCurrentAccount();
        String html = HtmlBuilder.withdrawPage(user.getFirstName() + " " + user.getLastName(),"",account,results);
        PrintWriter out = response.getWriter();
        out.write(html);
    }

    private static void depositPage(HttpServletRequest request, HttpServletResponse response, User user,String results) throws IOException {
        Account account =  user.getCurrentAccount();
        String html = HtmlBuilder.depositPage(user.getFirstName() + " " + user.getLastName(),"",account,results);
        PrintWriter out = response.getWriter();
        out.write(html);
    }
    private static void transferPage(HttpServletRequest request, HttpServletResponse response, User user,String results) throws IOException {
        Account account =  user.getCurrentAccount();
        String html = HtmlBuilder.transferPage(user.getFirstName() + " " + user.getLastName(),"",account,results);
        PrintWriter out = response.getWriter();
        out.write(html);
    }
}
