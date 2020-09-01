package com.banking.controller;

import com.banking.dao.AccountDao;
import com.banking.dao.TransactionDetailsDao;
import com.banking.dao.TransferDao;
import com.banking.dao.UserAccountDao;
import com.banking.dao.imp.AccountDaoImp;
import com.banking.dao.imp.TransactionDetailsDaoImp;
import com.banking.dao.imp.TransferDaoImp;
import com.banking.dao.imp.UserAccountDaoImp;
import com.banking.model.Account;
import com.banking.model.TransactionDetails;
import com.banking.model.User;
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
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(session != null && session.getAttribute("current_user") != null){
            pageBuilder(request,response,(User)session.getAttribute("current_user"));
        }
    }

    private static void pageBuilder(HttpServletRequest request,HttpServletResponse response, User user) throws IOException {
        try {
            int accountId = Integer.parseInt(request.getParameter("account_id"));
            AccountDao accountDao = new AccountDaoImp();
            Account account =  accountDao.findAccountById(accountId);
            UserAccountDao userAccountDao = new UserAccountDaoImp();
            account.setUserList(userAccountDao.findUsersByAccount(account.getAccountId()));
            TransactionDetailsDao transactionDetailsDao = new TransactionDetailsDaoImp();
            List<TransactionDetails> transactionDetails = transactionDetailsDao.findTransactionDetails();
            PrintWriter out = response.getWriter();
            String html = HtmlBuilder.accountSummaryUpper(user.getFirstName(), "Account Number : "+ account.getAccountId()) +
                    HtmlBuilder.accountTransactions(account,transactionDetails) +
                    HtmlBuilder.accountSummaryLower();
            out.write(html);
        } catch (Exception e){
            e.printStackTrace();
            response.sendError(404);
        }
    }
}
