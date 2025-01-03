package structures;

public class Sale {
    private int saleId;
    private String saleDate;
    private int customerId;
    private int productId;
    private int quantity;
    private double unitPrice;

    // Constructor
    public Sale(int saleId, String saleDate, int customerId, int productId, int quantity, double unitPrice) {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

	public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return unitPrice;
    }

    public void setPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
