package pack1;

import java.sql.Timestamp;

public class SellerProfit {
    private String sellerName;
    private String sellerNumber;
    private Timestamp billDate;
    private String productName;
    private double weight;
    private double pricePerKg;
    private double totalPrice;

    private double ownerProfit;
    private double workerCharges;
    private double sellerShare;

    public SellerProfit() {
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerNumber() {
        return sellerNumber;
    }

    public void setSellerNumber(String sellerNumber) {
        this.sellerNumber = sellerNumber;
    }

    public Timestamp getBillDate() {
        return billDate;
    }

    public void setBillDate(Timestamp billDate) {
        this.billDate = billDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getOwnerProfit() {
        return ownerProfit;
    }

    public void setOwnerProfit(double ownerProfit) {
        this.ownerProfit = ownerProfit;
    }

    public double getWorkerCharges() {
        return workerCharges;
    }

    public void setWorkerCharges(double workerCharges) {
        this.workerCharges = workerCharges;
    }

    public double getSellerShare() {
        return sellerShare;
    }

    public void setSellerShare(double sellerShare) {
        this.sellerShare = sellerShare;
    }
}