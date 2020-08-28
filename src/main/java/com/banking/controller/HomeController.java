package com.banking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController {
    public static void getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession(false) == null) {
            response.sendRedirect("http://localhost:6969/rocp-bank/api/login");
        } else if(request.getSession(false) != null){
            request.getRequestDispatcher("/accountSummary.html").forward(request, response);
        }
    }
}
