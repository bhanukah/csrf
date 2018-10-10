/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssd.sync;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bhhilk
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    private final String userID = "bhhilk";
    private final String password = "admin";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get request parameters for userid and password
        String user = request.getParameter("userid");
        String pwd = request.getParameter("password");

        if (userID.equals(user) && password.equals(pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute("userid", user);
            session.setAttribute("csrftoken", UUID.randomUUID());
            //session expire in 10 min
            session.setMaxInactiveInterval(10 * 60);
            response.sendRedirect("transfer.jsp");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Success</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Login failed</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
