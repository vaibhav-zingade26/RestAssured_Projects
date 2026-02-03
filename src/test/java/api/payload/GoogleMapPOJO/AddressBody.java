package api.payload.GoogleMapPOJO;

import java.util.List;

public class AddressBody {
    private List<Location> location;
    private double accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String[] types;
    private String webSites;
    private String language;

    public List<Location> getLocation() {
        return location;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public String[] getTypes() {
        return types;
    }

    public String getWebSites() {
        return webSites;
    }

    public String getLanguage() {
        return language;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public void setWebSites(String webSites) {
        this.webSites = webSites;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
