package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billId = request.getParameter("billId");
        String productName = request.getParameter("productName");
        double pricePerKg = Double.parseDouble(request.getParameter("pricePerKg"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        int numberOfPacks = Integer.parseInt(request.getParameter("numberOfPacks"));

        try (Connection conn = DBConnect.getcon()) {
            BillDAO dao = new BillDAO(conn);
            dao.saveProduct(billId, productName, pricePerKg, weight, pricePerKg * weight, 0, 0, numberOfPacks);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp");
    }
}