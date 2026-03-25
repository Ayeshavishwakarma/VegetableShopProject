package pack1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveProductServlet")
public class SaveProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection conn = DBConnect.getcon()) {

            BillDAO dao = new BillDAO(conn);

            // 1. Read bill details
            String shopNumber = request.getParameter("shop_number");
            String shopName = request.getParameter("shop_name");
            String sellerName = request.getParameter("seller_name");
            String sellerNumber = request.getParameter("seller_number");
            double workerCharges = Double.parseDouble(request.getParameter("worker_charges"));
            double totalPrice = Double.parseDouble(request.getParameter("total_price"));
            double ownerProfit = Double.parseDouble(request.getParameter("owner_profit"));

            // 2. Generate bill ID and current date
            String billId = "BILL_" + System.currentTimeMillis();
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());

            // 3. Save bill in bill table
            dao.saveBill(billId, shopNumber, shopName, currentDate, sellerName, sellerNumber, totalPrice, ownerProfit, workerCharges);

            // 4. Read products and save them
            int productCount = Integer.parseInt(request.getParameter("product_count"));
            List<Product> products = new ArrayList<>();

            for (int i = 1; i <= productCount; i++) {
                String productName = request.getParameter("product_name_" + i);
                double pricePerKg = Double.parseDouble(request.getParameter("price_per_kg_" + i));
                double weight = Double.parseDouble(request.getParameter("weight_" + i));
                int numberOfPacks = Integer.parseInt(request.getParameter("number_of_packs_" + i));

                double productTotal = pricePerKg * weight;

                dao.saveProduct(billId, productName, pricePerKg, weight, productTotal, ownerProfit, workerCharges, numberOfPacks);

                Product product = new Product(productName, pricePerKg, weight, productTotal, ownerProfit, workerCharges, numberOfPacks);
                products.add(product);
            }

            // 5. Send data to Billdetails.jsp
            request.setAttribute("shopNumber", shopNumber);
            request.setAttribute("shopName", shopName);
            request.setAttribute("sellerName", sellerName);
            request.setAttribute("sellerNumber", sellerNumber);
            request.setAttribute("workerCharges", workerCharges);
            request.setAttribute("ownerProfit", ownerProfit);
            request.setAttribute("billId", billId);
            request.setAttribute("currentDate", currentDate);
            request.setAttribute("products", products);
            request.setAttribute("totalPrice", totalPrice);

            // 6. Forward to bill page
            request.getRequestDispatcher("Billdetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error in SaveProductServlet: " + e.getMessage());
        }
    }
}