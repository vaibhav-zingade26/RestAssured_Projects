package api.payload.ECommerce;

public class CreatedOrders {
    private String[] orders;
    private String[] productOrderId;
    private String message;

    public String[] getOrders() {
        return orders;
    }

    public String[] getProductOrderId() {
        return productOrderId;
    }

    public String getMessage() {
        return message;
    }

    public void setOrders(String[] orders) {
        this.orders = orders;
    }

    public void setProductOrderId(String[] productOrderId) {
        this.productOrderId = productOrderId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
