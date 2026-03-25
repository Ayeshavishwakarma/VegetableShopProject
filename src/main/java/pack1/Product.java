package pack1;

public class Product {
    private String productName;
    private double pricePerKg;
    private double weight;
    private double totalPrice;
    private double ownerProfit;
    private double workerCharges;
    private int numberOfPacks;



    public Product() {}

    // Constructor
    public Product(String productName, double pricePerKg, double weight, double totalPrice, double ownerProfit, double workerCharges, int numberOfPacks) {
        this.productName = productName;
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.totalPrice = totalPrice;
        this.ownerProfit = ownerProfit;
        this.workerCharges = workerCharges;
        this.numberOfPacks = numberOfPacks;


    }

    public int getNumberOfPacks() {
		return numberOfPacks;
	}

    public void setNumberOfPacks(int numberOfPacks) {
        this.numberOfPacks = numberOfPacks;
    }

	// Getters and setters
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPricePerKg() { return pricePerKg; }
    public void setPricePerKg(double pricePerKg) { this.pricePerKg = pricePerKg; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public double getOwnerProfit() { return ownerProfit; }
    public void setOwnerProfit(double ownerProfit) { this.ownerProfit = ownerProfit; }

    public double getWorkerCharges() { return workerCharges; }
    public void setWorkerCharges(double workerCharges) { this.workerCharges = workerCharges; }
    
   
}