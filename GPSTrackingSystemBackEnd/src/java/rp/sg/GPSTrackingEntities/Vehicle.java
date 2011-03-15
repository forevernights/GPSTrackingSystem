

package rp.sg.GPSTrackingEntities;

import java.util.ArrayList;
public class Vehicle {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (this.vehicleID != other.vehicleID) {
            return false;
        }
        if (this.locationHistory != other.locationHistory && (this.locationHistory == null || !this.locationHistory.equals(other.locationHistory))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.vehicleID;
        hash = 41 * hash + (this.locationHistory != null ? this.locationHistory.hashCode() : 0);
        return hash;
    }
    int vehicleID;
    ArrayList<GeoPoint> locationHistory;

    public Vehicle(int vehicleID) {
        this.vehicleID = vehicleID;
        this.locationHistory = new ArrayList<GeoPoint>();
    }


    public ArrayList<GeoPoint> getLocationHistory() {
        return locationHistory;
    }

    public void setLocationHistory(ArrayList<GeoPoint> locationHistory) {
        this.locationHistory = locationHistory;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }
    public void trackLocation(GeoPoint currentLocation){
        locationHistory.add(currentLocation);
    }
    public GeoPoint retrieveCurrentLocation(){

        //retrive newest(final) location
        return locationHistory.get(locationHistory.size()-1);
    }
    @Override
    public String toString() {
        return "Vehicle{" + " id = " + vehicleID + "; locationHistory = " + locationHistory + '}';
    }    

}
