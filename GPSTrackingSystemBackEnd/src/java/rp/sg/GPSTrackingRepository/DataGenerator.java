package rp.sg.GPSTrackingRepository;

import java.util.ArrayList;
import rp.sg.GPSTrackingEntities.*;
public class DataGenerator {
    public DataGenerator(){}
    public ArrayList<Vehicle> generateFakeVehicleWithLoc(){
        //make fake vehicles
        Vehicle newVehicle = new Vehicle(1);
        Vehicle newVehicle1 = new Vehicle(2);
        
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

        //fake retrieve data from gps device, to perform detecting vehicle location
        newVehicle.trackLocation(new GeoPoint("1.4399679940455","103.78843203979"));
        newVehicle.trackLocation(new GeoPoint("1.4399679940455","103.78843203979"));
        newVehicle1.trackLocation(new GeoPoint("1.4449679940455","103.79863203979"));
        newVehicle1.trackLocation(new GeoPoint("1.4449679940455","103.81443203979"));
        //populate vehicleList
        vehicleList.add(newVehicle);
        vehicleList.add(newVehicle1);        
        return vehicleList;
    }
}
