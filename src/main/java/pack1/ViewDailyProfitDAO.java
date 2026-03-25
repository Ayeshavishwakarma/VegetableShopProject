package pack1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewDailyProfitDAO {
    private Connection connection;

    public ViewDailyProfitDAO(Connection connection) {
        this.connection = connection;
    }

    // 1. Daily total owner profit
    public double getOwnerProfitByDate(String date) throws SQLException {
        String sql = "SELECT SUM(OWNER_PROFIT) AS TOTAL_PROFIT " +
                     "FROM BILL " +
                     "WHERE TRUNC(BILL_DATE) = TO_DATE(?, 'YYYY-MM-DD')";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, date);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TOTAL_PROFIT");
                }
            }
        }
        return 0.0;
    }

    // 2. Daily bill list
    public List<Bill> getBillsByDate(String date) throws SQLException {
        String sql = "SELECT * FROM BILL WHERE TRUNC(BILL_DATE) = TO_DATE(?, 'YYYY-MM-DD')";
        List<Bill> bills = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, date);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Bill bill = new Bill();
                    bill.setBillId(rs.getString("BILL_ID"));
                    bill.setShopNumber(rs.getString("SHOP_NUMBER"));
                    bill.setShopName(rs.getString("SHOP_NAME"));
                    bill.setBillDate(rs.getTimestamp("BILL_DATE"));
                    bill.setSellerName(rs.getString("SELLER_NAME"));
                    bill.setSellerNumber(rs.getString("SELLER_NUMBER"));
                    bill.setTotalPrice(rs.getDouble("TOTAL_PRICE"));
                    bill.setOwnerProfit(rs.getDouble("OWNER_PROFIT"));
                    bill.setWorkerCharges(rs.getDouble("WORKER_CHARGES"));
                    bills.add(bill);
                }
            }
        }
        return bills;
    }

    // 3. Daily seller-wise profit
    public List<SellerProfit> getSellerProductProfitsByDate(String date) throws SQLException {
        String sql = "SELECT b.SELLER_NAME, b.SELLER_NUMBER, b.BILL_DATE, " +
                     "p.PRODUCT_NAME, p.WEIGHT, p.PRICE_PER_KG, p.TOTAL_PRICE, " +
                     "p.OWNER_PROFIT, b.WORKER_CHARGES, " +
                     "((p.PRICE_PER_KG * p.WEIGHT) - p.OWNER_PROFIT) AS SELLER_SHARE " +
                     "FROM BILL b " +
                     "JOIN PRODUCT p ON b.BILL_ID = p.BILL_ID " +
                     "WHERE TRUNC(b.BILL_DATE) = TO_DATE(?, 'YYYY-MM-DD') " +
                     "ORDER BY b.SELLER_NAME, b.BILL_DATE DESC";

        List<SellerProfit> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, date);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SellerProfit spp = new SellerProfit();
                    spp.setSellerName(rs.getString("SELLER_NAME"));
                    spp.setSellerNumber(rs.getString("SELLER_NUMBER"));
                    spp.setBillDate(rs.getTimestamp("BILL_DATE"));
                    spp.setProductName(rs.getString("PRODUCT_NAME"));
                    spp.setWeight(rs.getDouble("WEIGHT"));
                    spp.setPricePerKg(rs.getDouble("PRICE_PER_KG"));
                    spp.setTotalPrice(rs.getDouble("TOTAL_PRICE"));
                    spp.setOwnerProfit(rs.getDouble("OWNER_PROFIT"));
                    spp.setWorkerCharges(rs.getDouble("WORKER_CHARGES"));
                    spp.setSellerShare(rs.getDouble("SELLER_SHARE"));

                    list.add(spp);
                }
            }
        }
        return list;
    }

    // 4. Monthly total owner profit
    public double getOwnerProfitByMonth(String month) throws SQLException {
        String sql = "SELECT SUM(OWNER_PROFIT) AS TOTAL_PROFIT " +
                     "FROM BILL " +
                     "WHERE TRUNC(BILL_DATE, 'MM') = TO_DATE(?, 'YYYY-MM')";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, month);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TOTAL_PROFIT");
                }
            }
        }
        return 0.0;
    }

    // 5. Monthly seller-wise profit
    public List<SellerProfit> getSellerProductProfitsByMonth(String month) throws SQLException {
        String sql = "SELECT b.SELLER_NAME, b.SELLER_NUMBER, b.BILL_DATE, " +
                     "p.PRODUCT_NAME, p.WEIGHT, p.PRICE_PER_KG, p.TOTAL_PRICE, " +
                     "p.OWNER_PROFIT, b.WORKER_CHARGES, " +
                     "((p.PRICE_PER_KG * p.WEIGHT) - p.OWNER_PROFIT) AS SELLER_SHARE " +
                     "FROM BILL b " +
                     "JOIN PRODUCT p ON b.BILL_ID = p.BILL_ID " +
                     "WHERE TRUNC(b.BILL_DATE, 'MM') = TO_DATE(?, 'YYYY-MM') " +
                     "ORDER BY b.SELLER_NAME, b.BILL_DATE DESC";

        List<SellerProfit> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, month);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SellerProfit spp = new SellerProfit();
                    spp.setSellerName(rs.getString("SELLER_NAME"));
                    spp.setSellerNumber(rs.getString("SELLER_NUMBER"));
                    spp.setBillDate(rs.getTimestamp("BILL_DATE"));
                    spp.setProductName(rs.getString("PRODUCT_NAME"));
                    spp.setWeight(rs.getDouble("WEIGHT"));
                    spp.setPricePerKg(rs.getDouble("PRICE_PER_KG"));
                    spp.setTotalPrice(rs.getDouble("TOTAL_PRICE"));
                    spp.setOwnerProfit(rs.getDouble("OWNER_PROFIT"));
                    spp.setWorkerCharges(rs.getDouble("WORKER_CHARGES"));
                    spp.setSellerShare(rs.getDouble("SELLER_SHARE"));

                    list.add(spp);
                }
            }
        }
        return list;
    }

    // 6. Monthly seller-wise total owner profit (aggregated)
    public List<SellerProfit> getSellerProfitsByMonth(String month) throws SQLException {
        String sql = "SELECT SELLER_NAME, NVL(SUM(OWNER_PROFIT), 0) AS TOTAL_OWNER_PROFIT " +
                     "FROM BILL " +
                     "WHERE TRUNC(BILL_DATE, 'MM') = TO_DATE(?, 'YYYY-MM') " +
                     "GROUP BY SELLER_NAME " +
                     "ORDER BY TOTAL_OWNER_PROFIT DESC";

        List<SellerProfit> sellerProfits = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, month);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SellerProfit sp = new SellerProfit();
                    sp.setSellerName(rs.getString("SELLER_NAME"));
                    sp.setTotalPrice(rs.getDouble("TOTAL_OWNER_PROFIT"));
                    sellerProfits.add(sp);
                }
            }
        }
        return sellerProfits;
    }
}