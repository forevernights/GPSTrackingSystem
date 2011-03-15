/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTracking88.DatabaseTesting;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import rp.sg.GPSTrackingEntities.*;
import rp.sg.GPSTrackingDatabase.*;
/**
 *
 * @author NGUYEN TUAN VIET
 */
public class ManipulateAndStoreData {
    DatabaseAdapter dbAdapter;

    public ManipulateAndStoreData() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        dbAdapter = new DatabaseAdapter(null);
    }

    @After
    public void tearDown() {
        dbAdapter.rollBack();
        dbAdapter.closeDatabase();
    }
    @Test
    public void addLocationToLocationListOfVehicle(){        
        
    }

    @Test
    public void assignJobToTruck(){
        int sizeOfRecord = dbAdapter.retrieveDeliveryRecordList().size();
        DeliveryRecord newRecord = new DeliveryRecord(10, new Driver(0,null), new Vehicle(0));
        dbAdapter.storeDeliveryRecord(newRecord);
        int newSizeOfRecord = dbAdapter.retrieveDeliveryRecordList().size();
        //check whether the recordlist increases after adding one more record
        assertEquals(sizeOfRecord+1,newSizeOfRecord);
    }
    @Test
    public void trackingLocationOfTruck(){
        DeliveryRecord record = new DeliveryRecord(11,dbAdapter.findDriverById(1),dbAdapter.findVehicleById(1));
        
    }
    @Test
    public void addNewDeliveryRecord(){
        Vehicle vehicle = dbAdapter.findVehicleById(1);
        Driver driver = dbAdapter.findDriverById(1);
        DeliveryRecord record = new DeliveryRecord(11, driver, vehicle);
        dbAdapter.storeDeliveryRecord(record);
        dbAdapter.closeDatabase();
        dbAdapter = new DatabaseAdapter(null);
        DeliveryRecord newRecord = dbAdapter.findDeliveryRecord(11, driver, vehicle);
        System.out.print(newRecord);
    }
    @Test
    public void deliverOrderSuccess(){
        Order order = dbAdapter.findOrderById(1);
        order.deliverOrder();
        //check whether status of order turn into "delivered" === false
        assertEquals(order.returnDeliverStat(),true);
    }
    @Test
    public void deliverAllOrderInDeliveryRecord(){
        DeliveryRecord record = dbAdapter.findDeliveryRecord(4, null, null);
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderList = record.getOrderList();
        for(Order order:orderList){
            order.deliverOrder();
        }
        //check whether status of DeliveryRecord turn "delivered" if all order in orderlist delivered
        assertEquals(record.isAllOrderDelivered(),true);
    }
    @Test
    public void checkStatusOfDeliveryRecordIfNotDeliverAllOrder(){
        DeliveryRecord record = dbAdapter.findDeliveryRecord(4, null, null);
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderList = record.getOrderList();
        //break after deliver one order
        for(Order order:orderList){
            order.deliverOrder();
            break;
        }
        //check whether status of DeliveryRecord turn "delivered" if all order in orderlist delivered
        assertEquals(record.isAllOrderDelivered(),false);
    }
}