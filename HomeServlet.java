/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rames
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(false);

        String user = "ramesh kumar";

        // STEP 1: check if session exists
       /* if (session != null && session.getAttribute("username") != null) {
            user = (String) session.getAttribute("username");
        }

        // STEP 2: if no session, check cookies
        if (user == null) {

            Cookie[] cookies = req.getCookies();
            String cu = null, cp = null;

            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("uname")) {
                        cu = c.getValue();
                    }
                    if (c.getName().equals("upass")) {
                        cp = c.getValue();
                    }
                }
            }

            // if cookies found → create new session
            if (cu != null && cp != null) {
                session = req.getSession();
                session.setAttribute("username", cu);
                user = cu; // auto login
            }
        }

        // STEP 3: If still no user → go to login
        if (user == null) {
            res.sendRedirect("login.html");
            return;
        }*/

        // STEP 4: Show Home Page
        res.setContentType("text/html;charset=UTF-8");
PrintWriter out = res.getWriter();

out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Home</title>");
out.println("<style>");
out.println("body { font-family: Arial, sans-serif; background:#f4f6f9; margin:0; }");
out.println(".container { max-width:600px; margin:80px auto; background:white; padding:40px; border-radius:10px; box-shadow:0 0 10px rgba(0,0,0,0.1); text-align:center; }");
out.println("h2 { margin-bottom:20px; color:#333; }");
out.println("a { display:inline-block; padding:10px 20px; background:#007bff; color:white; text-decoration:none; border-radius:5px; }");
out.println("a:hover { background:#0056b3; }");
out.println("</style>");
out.println("</head>");
out.println("<body>");

out.println("<div class='container'>");
out.println("<h2>Welcome " + user + "</h2>");
out.println("<a href='LogoutServlet'>Logout</a>");
out.println("</div>");

out.println("</body>");
out.println("</html>");
  }}

    
