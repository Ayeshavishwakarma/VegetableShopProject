
package pack1;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/OwnerLoginServlet")
public class OwnerLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String username = req.getParameter("uname");
	    String password = req.getParameter("pword");

	    // Check if username/password is empty
	    if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
	        req.setAttribute("errorMessage", "Username and password are required.");
	        RequestDispatcher rd = req.getRequestDispatcher("OwnerLogin.jsp");
	        rd.forward(req, res);
	        return;
	    }

	    OwnerLoginDAO dao = new OwnerLoginDAO();
	    OwnerBean obean = dao.checkOwnerLogin(username, password);

	    if (obean != null) {
	        HttpSession session = req.getSession();
	        session.setAttribute("obean", obean);
	        res.sendRedirect("OwnerHome.jsp");
	    } else {
	        System.out.println("Login failed for username: " + username); // Debugging log
	        req.setAttribute("errorMessage", "Invalid username or password. Please try again.");
	        RequestDispatcher rd = req.getRequestDispatcher("OwnerLogin.jsp");
	        rd.forward(req, res);
	    }
	}
}