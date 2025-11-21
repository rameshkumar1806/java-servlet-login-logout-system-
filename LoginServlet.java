package com.project;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Read cookies
        Cookie[] ck = req.getCookies();
        String cu = null, cp = null;

        if(ck != null){
            for(Cookie c : ck){
                if(c.getName().equals("uname")) cu = c.getValue();
                if(c.getName().equals("upass")) cp = c.getValue();
            }
        }

        // CASE 1: First login → blank password NOT allowed
        if ((pass == null || pass.trim().isEmpty()) && cp == null) {
            out.println("<h3>Password required!</h3>");
            out.println("<a href='login.html'>Try Again</a>");
            return;
        }

        boolean valid = false;

        // CASE 2: Cookie based login
        if (cp != null && cu != null) {
            if (user.trim().equals(cu.trim()) &&
               (pass.trim().equals(cp.trim()) || pass.trim().isEmpty())) {

                valid = true;
            }
        } 
        else {
            // FIRST TIME NORMAL LOGIN (file-based)
            String path = getServletContext().getRealPath("/WEB-INF/users.txt");
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    if (data[0].trim().equals(user.trim()) &&
                        data[1].trim().equals(pass.trim())) {

                        valid = true;
                        break;
                    }
                }
            }
            br.close();
        }

        if(valid){

            HttpSession session = req.getSession();
            session.setAttribute("username", user);

            // remember me
            String remember = req.getParameter("remember");

            if("on".equals(remember)){
                Cookie c1 = new Cookie("uname", user);
                Cookie c2 = new Cookie("upass", pass);

                c1.setMaxAge(60 * 60 * 24 * 7);
                c2.setMaxAge(60 * 60 * 24 * 7);

                res.addCookie(c1);
                res.addCookie(c2);
            }

            res.sendRedirect("HomeServlet");
        }
        else {
            out.println("<h3>Invalid Login</h3>");
            out.println("<a href='login.html'>Try Again</a>");
        }
    }
}
