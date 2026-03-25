package pack1;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private final Connection conn;

    public BillDAO(Connection conn) {
        this.conn = conn;
    }

    public void saveBill(String billId, String shopNumber, String shopName, Timestamp current, String sellerName, String sellerNumber, double totalPrice, double ownerProfit, double workerCharges) throws SQLException {
        String sql = "INSERT INTO bill (BILL_ID, SHOP_NUMBER, SHOP_NAME, BILL_DATE, SELLER_NAME, SELLER_NUMBER, TOTAL_PRICE, OWNER_PROFIT, WORKER_CHARGES) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, billId);
            stmt.setString(2, shopNumber);
            stmt.setString(3, shopName);
            stmt.setTimestamp(4, current);
            stmt.setString(5, sellerName);
            stmt.setString(6, sellerNumber);
            stmt.setDouble(7, totalPrice);
            stmt.setDouble(8, ownerProfit);
            stmt.setDouble(9, workerCharges);
            stmt.executeUpdate();
        }
    }

    public void saveProduct(String billId, String productName, double pricePerKg, double weight, double totalPrice, double ownerProfit, double workerCharges, int numberOfPacks) throws SQLException {
        String sql = "INSERT INTO product (BILL_ID, PRODUCT_NAME, PRICE_PER_KG, WEIGHT, TOTAL_PRICE, OWNER_PROFIT, WORKER_CHARGES, NUMBER_OF_PACKS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, billId);
            stmt.setString(2, productName);
            stmt.setDouble(3, pricePerKg);
            stmt.setDouble(4, weight);
            stmt.setDouble(5, totalPrice);
            stmt.setDouble(6, ownerProfit);
            stmt.setDouble(7, workerCharges);
            stmt.setInt(8, numberOfPacks);
            stmt.executeUpdate();
        }
    }

    public List<Product> getProductsByBillId(String billId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT PRODUCT_NAME, PRICE_PER_KG, WEIGHT, TOTAL_PRICE, OWNER_PROFIT, WORKER_CHARGES, NUMBER_OF_PACKS FROM product WHERE BILL_ID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                        rs.getString("PRODUCT_NAME"),
                        rs.getDouble("PRICE_PER_KG"),
                        rs.getDouble("WEIGHT"),
                        rs.getDouble("TOTAL_PRICE"),
                        rs.getDouble("OWNER_PROFIT"),
                        rs.getDouble("WORKER_CHARGES"),
                        rs.getInt("NUMBER_OF_PACKS")
                    ));
                }
            }
        }
        return products;
    }
}