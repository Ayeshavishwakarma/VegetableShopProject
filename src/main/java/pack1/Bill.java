package pack1;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Bill {
    private String billId;
    private String shopNumber;
    private String shopName;
    private Timestamp billDate;   // CHANGED
    private String sellerName;
    private String sellerNumber;
    private double totalPrice;
    private double ownerProfit;
    private double workerCharges;
    private List<Product> productList;

    public Bill() {}

    // Constructor
    public Bill(String billId, String shopNumber, String shopName, Timestamp billDate, String sellerName, String sellerNumber, double totalPrice, double ownerProfit, double workerCharges) {
        this.billId = billId;
        this.shopNumber = shopNumber;
        this.shopName = shopName;
        this.billDate = billDate;
        this.sellerName = sellerName;
        this.sellerNumber = sellerNumber;
        this.totalPrice = totalPrice;
        this.ownerProfit = ownerProfit;
        this.workerCharges = workerCharges;
    }

    public String getBillId() { return billId; }
    public void setBillId(String billId) { this.billId = billId; }

    public String getShopNumber() { return shopNumber; }
    public void setShopNumber(String shopNumber) { this.shopNumber = shopNumber; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public Timestamp getBillDate() { return billDate; }   // CHANGED
    public void setBillDate(Timestamp billDate) { this.billDate = billDate; }   // CHANGED

    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }

    public String getSellerNumber() { return sellerNumber; }
    public void setSellerNumber(String sellerNumber) { this.sellerNumber = sellerNumber; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getOwnerProfit() { return ownerProfit; }
    public void setOwnerProfit(double ownerProfit) { this.ownerProfit = ownerProfit; }

    public double getWorkerCharges() { return workerCharges; }
    public void setWorkerCharges(double workerCharges) { this.workerCharges = workerCharges; }

    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }

    public String getFormattedDate() {
        if (billDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            return sdf.format(billDate);
        }
        return "नहीं मिला";
    }
}