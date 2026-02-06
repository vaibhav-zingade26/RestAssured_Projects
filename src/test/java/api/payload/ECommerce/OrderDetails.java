package api.payload.ECommerce;

public class OrderDetails {
    private String country;
    private String productOrderedId;

    public String getCountry() {
        return country;
    }

    public String getProductOrderedId() {
        return productOrderedId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProductOrderedId(String productOrderedId) {
        this.productOrderedId = productOrderedId;
    }
}
