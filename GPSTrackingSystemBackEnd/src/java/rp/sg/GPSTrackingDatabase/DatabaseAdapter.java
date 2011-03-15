package rp.sg.GPSTrackingDatabase;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.config.ObjectClass;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import rp.sg.GPSTrackingEntities.*;

// DatabaseAdapter class -- Author: Bui Thi Ngoc Anh
public class DatabaseAdapter {

    private ObjectContainer db;
    private DatabaseInstance dbInstance = new DatabaseInstance();
    ArrayList<Driver> driverList = new ArrayList<Driver>();
    ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
    ArrayList<Goods> goodsList = new ArrayList<Goods>();
    ArrayList<Order> orderList = new ArrayList<Order>();
    
    ArrayList<GeoPoint> geoPointList = new ArrayList<GeoPoint>();
    private ServletContext context;

    public DatabaseAdapter(ServletContext context) {
        this.context = context;
        db = dbInstance.getDatabase(context);
    }

    public void closeDatabase() {
        if (db != null) {
            db.close();
        }
    }

//Driver
    public void storeDriver(Driver driver) {
        db.commit();
        db.store(driver);        
        System.out.println("Saved driver to database");
    }

    public ArrayList<Driver> retrieveDriverList() {
        ObjectSet result = db.queryByExample(Driver.class);
        while (result.hasNext()) {
            driverList.add((Driver) result.next());
        }
        return driverList;
    }

    //find driver by name or id
    public Driver findDriverByName(String nameDriver) {
        Driver driver = new Driver(0, nameDriver);
        ObjectSet result = db.queryByExample(driver);
        if (result.hasNext()) {
            return (Driver) result.next();
        }
        return null;
    }

    public Driver findDriverById(int idDriver) {
        Driver driver = new Driver(idDriver, null);
        ObjectSet result = db.queryByExample(driver);
        if (result.hasNext()) {
            return (Driver) result.next();
        }
        return null;
    }

    public void printDriverList() {
        ObjectSet result = db.queryByExample(Driver.class);
        while (result.hasNext()) {
            System.out.println(result.next());
        }

    }

    //Vehicle
    public void storeVehicle(Vehicle vehicle) {
        db.commit();
        db.store(vehicle);
        System.out.print("Vehicle stored");
    }

    public ArrayList<Vehicle> retrieveVehicleList() {
        ObjectSet result = db.queryByExample(Vehicle.class);
        while (result.hasNext()) {
            vehicleList.add((Vehicle) result.next());
        }
        return vehicleList;
    }

    public Vehicle findVehicleById(int idVehicle) {
        Vehicle vehicle = new Vehicle(idVehicle);
        ObjectSet result = db.queryByExample(vehicle);
        if (result.hasNext()) {
            return (Vehicle) result.next();
        }
        return null;
    }   

    public void printVehicleList() {
        ObjectSet result = db.queryByExample(Vehicle.class);
        while (result.hasNext()) {
            System.out.println(result.next());
        }

    }

    //Goods
    public void storeGoods(Goods goods) {
        db.commit();
        db.store(goods);
    }

    public ArrayList<Goods> retrieveGoodsList() {
        ObjectSet result = db.queryByExample(Goods.class);
        while (result.hasNext()) {
            goodsList.add((Goods) result.next());
        }
        return goodsList;
    }

    //find goods by name or id
    public Goods findGoodsByName(String nameGoods) {
        Goods goods = new Goods(0, nameGoods, 0);
        ObjectSet result = db.queryByExample(goods);
        if (result.hasNext()) {
            return (Goods) result.next();
        }
        return null;
    }

    public Goods findGoodsById(int idGoods) {
        Goods goods = new Goods(idGoods, null, 0);
        ObjectSet result = db.queryByExample(goods);
        if (result.hasNext()) {
            return (Goods) result.next();
        }
        return null;
    }

    public void printGoodsList() {
        ObjectSet result = db.queryByExample(Goods.class);
        while (result.hasNext()) {
            System.out.println(result.next());
        }

    }

    // remove goods by name
    public void removeGoods() {
        db.commit();
        String nameGoods = null;
        ObjectSet result = db.queryByExample(new Goods(0, nameGoods, 0));
        Goods found = (Goods) result.next();
        db.delete(found);
        System.out.println("Deleted " + found);

    }

    //Order
    public void storeOrder(Order order) {
        db.commit();
        db.store(order);
    }

    public ArrayList<Order> retrieveOrderList() {
        ObjectSet result = db.queryByExample(Order.class);
        while (result.hasNext()) {
            orderList.add((Order) result.next());
        }
        return orderList;
    }

    public Order findOrderById(int idOrder) {
        Order order = new Order(idOrder, null);
        ObjectSet result = db.queryByExample(order);
        if (result.hasNext()) {
            return (Order) result.next();
        }
        return null;
    }

    public void printOrderList() {
        ObjectSet result = db.queryByExample(Order.class);
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }

    //DeliveryRecord
    public void storeDeliveryRecord(DeliveryRecord deliveryRecord) {
        db.commit();
        db.store(deliveryRecord);
    }

    public ArrayList<DeliveryRecord> retrieveDeliveryRecordList() {
        ArrayList<DeliveryRecord> deliveryRecordList = new ArrayList<DeliveryRecord>();
        ObjectSet result = db.queryByExample(DeliveryRecord.class);
        while (result.hasNext()) {
            deliveryRecordList.add((DeliveryRecord) result.next());
        }
        return deliveryRecordList;
    }
    public void updateLocationOfVehicle(int recordId,GeoPoint currentLoc){
        db.ext().configure().updateDepth(3);
        DeliveryRecord record = this.findDeliveryRecord(recordId, null, null);
        db.activate(record, 3);
        Vehicle vehicle = record.getVehicle();
        db.activate(vehicle, 2);
        vehicle.trackLocation(currentLoc);
        db.commit();
        db.store(record);
    }
    public void deliverOrderOfDeliveryRecord(int recordId,int orderId){
        db.ext().configure().updateDepth(2);
        DeliveryRecord record = this.findDeliveryRecord(recordId, null, null);
        db.activate(record, 2);
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderList = record.getOrderList();
        for(Order order:orderList){
            if(order.getOrderID()==orderId){
                Order updateOrder = order;
                db.activate(updateOrder, 1);
                updateOrder.deliverOrder();
                db.commit();
                db.store(updateOrder);
            }
        }
        
    }
    public void printDeliveryRecordList() {
        ObjectSet result = db.queryByExample(DeliveryRecord.class);
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }
    public DeliveryRecord findDeliveryRecord(int DeliveryRecordID, Driver driver, Vehicle vehicle) {
        DeliveryRecord deliveryRecord = new DeliveryRecord(DeliveryRecordID, driver, vehicle);
        ObjectSet result = db.queryByExample(deliveryRecord);
        if (result.hasNext()) {
            return (DeliveryRecord) result.next();
        }
        return null;
    }

// Geo Point
    public void storeGeoPoint(GeoPoint geoPoint) {
        db.commit();
        db.store(geoPoint);
    }

    public ArrayList<GeoPoint> retrieveGeoPointList() {
        ObjectSet result = db.queryByExample(GeoPoint.class);
        while (result.hasNext()) {
            geoPointList.add((GeoPoint) result.next());
        }
        return geoPointList;
    }

    public GeoPoint findGeoPointBylnglat(String lng, String lat) {
        GeoPoint geoPoint = new GeoPoint(lng, lat);
        ObjectSet result = db.queryByExample(geoPoint);
        if (result.hasNext()) {
            return (GeoPoint) result.next();
        }
        return null;
    }

    public void printGeoPointList() {
        ObjectSet result = db.queryByExample(GeoPoint.class);
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }


    //helper functions --author: Nguyen Tuan Viet
    public void removeAllGeoPointObj(){
        ObjectSet result = db.query(GeoPoint.class);
        while(result.hasNext()){
            db.delete(result.next());
            System.out.println("One GeoPoint object deleted");
        }
    }
    public void removeAllVehicleObj(){
        ObjectSet result = db.query(Vehicle.class);
        while(result.hasNext()){
            db.delete(result.next());
            System.out.println("One vehicle obj deleted...");
        }
    }

    //author -- Nguyen Tuan Viet
    //additional functions

    public void commit(){
        db.commit();
    }
    public void rollBack(){
        db.rollback();
    }

}
