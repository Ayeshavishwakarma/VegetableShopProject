package pack1;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewSellerDetailsServlet")
public class ViewSellerDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ViewSellerDetailsDAO dao = new ViewSellerDetailsDAO();

        List<Bill> billList = dao.getAllBills();

        if (billList == null) {
            System.out.println("❌ billList is null");
        } else {
            System.out.println("✅ Number of bills fetched in servlet: " + billList.size());
        }

        if (billList == null || billList.isEmpty()) {
            System.out.println("⚠️ No bills found in the database.");
        } else {
            System.out.println("✅ Bills found: " + billList.size());
        }

        request.setAttribute("billList", billList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewSellerDetails.jsp");
        dispatcher.forward(request, response);
    }
}