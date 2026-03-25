package pack1;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/OwnerRegistrationServlet")
public class OwnerRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Retrieve form parameters
        String username = req.getParameter("uname");
        String password = req.getParameter("pword");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String addr = req.getParameter("addr");
        String mid = req.getParameter("mid");
        String phone = req.getParameter("phno");

        // Validate required fields
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            fname == null || fname.trim().isEmpty() ||
            lname == null || lname.trim().isEmpty() ||
            addr == null || addr.trim().isEmpty() ||
            mid == null || mid.trim().isEmpty() ||
            phone == null || phone.trim().isEmpty()) {
            
            req.setAttribute("msg", "All fields are required.");
            RequestDispatcher rd = req.getRequestDispatcher("OwnerRegistration.jsp");
            rd.forward(req, res);
            return;
        }

        // Register owner using DAO
        OwnerRegistrationDAO dao = new OwnerRegistrationDAO();
        boolean isRegistered = dao.registerOwner(username, password, fname, lname, addr, mid, phone);

        // Forward based on registration result
        if (isRegistered) {
            req.setAttribute("msg", "Registration successful! Please login.");
            RequestDispatcher rd = req.getRequestDispatcher("OwnerLogin.jsp");
            rd.forward(req, res);
        } else {
            req.setAttribute("msg", "Registration failed. Try again.");
            RequestDispatcher rd = req.getRequestDispatcher("OwnerRegistration.jsp");
            rd.forward(req, res);
        }
    }
}
