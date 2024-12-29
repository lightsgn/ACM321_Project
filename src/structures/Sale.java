package structures;

public class Sale {
    private int saleId;
    private int productId;
    private int quantity;
    private double price;
    private String saleDate;

    public Sale(int saleId, int productId, int quantity, double price, String saleDate) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.saleDate = saleDate;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSaleDate() {
        return saleDate;
    }
}
