package api.payload.GoogleMapPOJO;

public class PutMethod {
    private String place_id;
    private String address;
    private String key;

    public String getPlace_id() {
        return place_id;
    }

    public String getAddress() {
        return address;
    }

    public String getKey() {
        return key;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
