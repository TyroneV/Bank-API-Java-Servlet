package com.banking.controller;

import com.banking.model.Account;
import com.banking.model.User;
import com.banking.service.BankingService;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountUpdateController {

    public static void getAccountUpdatePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user.getRole().getRoleId() == 1;
            if (!check || request.getParameter("update_id") == "") {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check) {
                updateAccountPage(response,request,"",Integer.parseInt(request.getParameter("update_id")));
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    public static void submitAccountUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user.getRole().getRoleId() == 1;
            if (!check || request.getParameter("update_id") == "") {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check) {
                Account account = new Account(Integer.parseInt(request.getParameter("account_id")),
                        Double.parseDouble(request.getParameter("balance")),
                        BankingService.findStatus(Integer.parseInt(request.getParameter("account_status"))),
                        BankingService.findType(Integer.parseInt(request.getParameter("account_type"))));
               if(BankingService.updateAccount(account) != null) {
                   updateAccountPage(response, request, "<h4 class=\"success\">Account has been updated!</h4>", account.getAccountId());
               } else {
                   updateAccountPage(response, request, "<h4 class=\"error\">Failed to update Account!</h4>", account.getAccountId());
               }
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    private static void updateAccountPage(HttpServletResponse response,HttpServletRequest request,String result,int accountID) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("current_user");
        Account account = BankingService.findAccount(accountID);
        PrintWriter out = response.getWriter();
        String html = HtmlBuilder.accountUpdatePage(user,account,result);
        out.print(html);
    }
}
