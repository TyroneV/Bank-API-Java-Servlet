package com.banking.controller;

import com.banking.model.User;
import com.banking.security.BankSecurity;
import com.banking.service.BankingService;
import com.banking.viewbuilder.HtmlBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateController {
    //User Update
    public static void getUserUpdatePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check) {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check) {
                updateUserPage(response,request,"");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }
    public static void getUserUpdateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("current_user");
            boolean check = session != null && user != null;
            if (!check || request.getParameter("update_id") == "") {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            } else if (check && user.getRole().getRoleId() == 1) {
                updateUserPageAdmin(response,request,"");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }
    public static void sendUpdate(HttpServletRequest request, HttpServletResponse response,boolean adminUpdate) throws IOException{
        try {
            HttpSession session = request.getSession(false);
            User currentUser = (User)session.getAttribute("current_user");
            User targetUser = currentUser;
            if(adminUpdate){
                targetUser = (User)session.getAttribute("updated_user");
            }
            boolean check = session != null && targetUser != null;
            if (check) {
                User updateUser = new User();
                BankSecurity bankSecurity = new BankSecurity();
                updateUser.setUsername(request.getParameter("username"));
                updateUser.setUserId(targetUser.getUserId());
                if(!request.getParameter("password").equals("")){
                    updateUser.setPassword(bankSecurity.passwordHash(request.getParameter("password")));
                }else {
                    updateUser.setPassword(targetUser.getPassword());
                }
                updateUser.setFirstName(request.getParameter("first_name"));
                updateUser.setLastName(request.getParameter("last_name"));
                updateUser.setEmail(request.getParameter("email"));
                if(adminUpdate){
                    updateUser.setRole(BankingService.findRole(Integer.parseInt(request.getParameter("user_type"))));
                }else {
                    updateUser.setRole(targetUser.getRole());
                }
                updateUser.setAccounts(targetUser.getAccounts());
                User target = BankingService.findUserByUsername(updateUser.getUsername());
                if(target == null || target.getUserId() == updateUser.getUserId()){
                    if(currentUser.getUserId() == targetUser.getUserId()) {
                        session.setAttribute("current_user", BankingService.updateUser(updateUser));
                    }else {
                        BankingService.updateUser(updateUser);
                    }
                    updateUserPage(response,request,"<h4 class=\"success\">User has been updated!</h4>");
                }else {
                    updateUserPage(response,request,"<h4 class=\"error\">Username already exist! please try again!</h4>");
                }
            } else {
                response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
            }
        } catch (NullPointerException e){
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        }
    }

    private static void updateUserPageAdmin(HttpServletResponse response,HttpServletRequest request,String result) throws IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        User user = BankingService.findUser(Integer.parseInt(request.getParameter("update_id")));
        session.setAttribute("updated_user", user);
        PrintWriter out = response.getWriter();
        String html = HtmlBuilder.updatePage(user,result,true);
        out.print(html);
    }

    private static void updateUserPage(HttpServletResponse response,HttpServletRequest request,String result) throws IOException {
        response.setContentType("text/html");
        User user = (User)request.getSession(false).getAttribute("current_user");
        PrintWriter out = response.getWriter();
        String html = HtmlBuilder.updatePage(user,result,false);
        out.print(html);
    }
}
