package pack1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewSellerDetailsDAO {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "lion";

    public List<Bill> getAllBills() {
        List<Bill> billList = new ArrayList<>();

        String billQuery = "SELECT BILL_ID, SHOP_NUMBER, SHOP_NAME, BILL_DATE, SELLER_NAME, SELLER_NUMBER, TOTAL_PRICE, OWNER_PROFIT, WORKER_CHARGES " +
                           "FROM BILL ORDER BY BILL_DATE DESC";

        // IMPORTANT: change column names if your PRODUCT table names are different
        String productQuery = "SELECT PRODUCT_NAME, PRICE_PER_KG, WEIGHT, TOTAL_PRICE, OWNER_PROFIT, WORKER_CHARGES, NUMBER_OF_PACKS " +
                              "FROM PRODUCT WHERE BILL_ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement billStmt = conn.prepareStatement(billQuery);
             ResultSet billRs = billStmt.executeQuery()) {

            System.out.println("Connected to database successfully");

            while (billRs.next()) {
                Bill bill = new Bill();

                String billId = billRs.getString("BILL_ID");

                bill.setBillId(billId);
                bill.setShopNumber(billRs.getString("SHOP_NUMBER"));
                bill.setShopName(billRs.getString("SHOP_NAME"));
                bill.setBillDate(billRs.getTimestamp("BILL_DATE"));
                bill.setSellerName(billRs.getString("SELLER_NAME"));
                bill.setSellerNumber(billRs.getString("SELLER_NUMBER"));
                bill.setTotalPrice(billRs.getDouble("TOTAL_PRICE"));
                bill.setOwnerProfit(billRs.getDouble("OWNER_PROFIT"));
                bill.setWorkerCharges(billRs.getDouble("WORKER_CHARGES"));

                // Fetch products for this bill
                List<Product> productList = new ArrayList<>();

                try (PreparedStatement productStmt = conn.prepareStatement(productQuery)) {
                    productStmt.setString(1, billId);

                    try (ResultSet productRs = productStmt.executeQuery()) {
                        while (productRs.next()) {
                            Product product = new Product();

                            product.setProductName(productRs.getString("PRODUCT_NAME"));
                            product.setPricePerKg(productRs.getDouble("PRICE_PER_KG"));
                            product.setWeight(productRs.getDouble("WEIGHT"));
                            product.setTotalPrice(productRs.getDouble("TOTAL_PRICE"));
                            product.setOwnerProfit(productRs.getDouble("OWNER_PROFIT"));
                            product.setWorkerCharges(productRs.getDouble("WORKER_CHARGES"));
                            product.setNumberOfPacks(productRs.getInt("NUMBER_OF_PACKS"));

                            productList.add(product);
                        }
                    }
                }

                // Set products into bill
                bill.setProductList(productList);

                billList.add(bill);
            }

            System.out.println("Total bills fetched: " + billList.size());

        } catch (SQLException e) {
            System.out.println("Error while fetching seller details");
            e.printStackTrace();
        }

        return billList;
    }
}