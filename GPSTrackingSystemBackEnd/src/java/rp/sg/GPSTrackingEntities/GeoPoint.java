
package rp.sg.GPSTrackingEntities;

public class GeoPoint {
    private String lng;
    private String lat;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GeoPoint other = (GeoPoint) obj;
        if ((this.lng == null) ? (other.lng != null) : !this.lng.equals(other.lng)) {
            return false;
        }
        if ((this.lat == null) ? (other.lat != null) : !this.lat.equals(other.lat)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.lng != null ? this.lng.hashCode() : 0);
        hash = 67 * hash + (this.lat != null ? this.lat.hashCode() : 0);
        return hash;
    }

    public GeoPoint() {
    }

    public GeoPoint(String lat, String lng) {
        this.lng = lng;
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "GeoPoint{" + " lat=" + lat + "; lng=" + lng + '}';
    }
    
}
