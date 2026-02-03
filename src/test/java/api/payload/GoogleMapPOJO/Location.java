package api.payload.GoogleMapPOJO;

import java.time.LocalDate;

public class Location {
    private float lat;
    private float lng;

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
