package pack1;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewDailyProfitServlet")
public class ViewDailyProfitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String month = request.getParameter("month");

        try {
            ViewDailyProfitDAO dao = new ViewDailyProfitDAO(DBConnect.getcon());

            double totalProfit = 0.0;
            List<Bill> bills = null;
            List<SellerProfit> sellerProfits = null;
            List<SellerProfit> sellerProductProfits = null;

            if (date != null && !date.isEmpty()) {
                // DAILY REPORT
                totalProfit = dao.getOwnerProfitByDate(date);
                bills = dao.getBillsByDate(date);
                sellerProfits = dao.getSellerProductProfitsByDate(date);
                sellerProductProfits = dao.getSellerProductProfitsByDate(date);

                request.setAttribute("selectedDate", date);
                request.setAttribute("profitType", "daily");

            } else if (month != null && !month.isEmpty()) {
                // MONTHLY REPORT
                totalProfit = dao.getOwnerProfitByMonth(month);

                // Optional: if you don't have monthly bills method, keep bills null
                bills = null;

                sellerProfits = dao.getSellerProfitsByMonth(month);
                sellerProductProfits = dao.getSellerProductProfitsByMonth(month);

                request.setAttribute("selectedMonth", month);
                request.setAttribute("profitType", "monthly");
            }

            request.setAttribute("totalProfit", totalProfit);
            request.setAttribute("bills", bills);
            request.setAttribute("sellerProfits", sellerProfits);
            request.setAttribute("sellerProductProfits", sellerProductProfits);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ViewDailyProfit.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching data.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ViewDailyProfit.jsp");
            dispatcher.forward(request, response);
        }
    }
}