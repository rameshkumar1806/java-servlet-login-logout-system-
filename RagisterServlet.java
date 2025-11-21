/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rames
 */
@WebServlet(name = "RagisterServlet", urlPatterns = {"/RagisterServlet"})
public class RagisterServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
         res. setContentType("text/html;charset=UTF-8");

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        String path = getServletContext().getRealPath("/WEB-INF/users.txt");

        // check if user exists
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while((line = br.readLine()) != null){
            String[] data = line.split(",");
            if(data[0].equals(user)){
                res.getWriter().println("<h3>User Already Exists</h3>");
                return;
            }
        }
        br.close();

        // save user
        FileWriter fw = new FileWriter(path, true);
        fw.write(user + "," + pass + "\n");
        fw.close();

        res.getWriter().println("<h2>Registered Successfully!</h2>");
        res.getWriter().println("<a href='login.html'>Login</a>");
    }
}