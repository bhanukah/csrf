/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssd.sync;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
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
@WebServlet("/transfer")
public class Transfer extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        if(session.getAttribute("userid") == null){
	response.sendRedirect("index.jsp");
        }else {
            response.sendRedirect("transfer.jsp");
        }        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userid") == null){
            processRequest(request, response);
            return;
        }
        String accid = request.getParameter("accid");
	String amt = request.getParameter("amount");
        String clientCsrf = request.getParameter("csrf");
	String csrf = session.getAttribute("csrftoken").toString();
        // reset csrf to new
        session.setAttribute("csrftoken", UUID.randomUUID());
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (session.getAttribute("csrftoken") != null && clientCsrf.equals(csrf)) {
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Success</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Transfered "+amt+" to "+accid+"</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }else {
            try {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Success</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Transfer Failed</h1>");
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
