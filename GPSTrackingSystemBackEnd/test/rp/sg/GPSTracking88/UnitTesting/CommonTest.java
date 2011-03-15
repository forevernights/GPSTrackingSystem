package rp.sg.GPSTracking88.UnitTesting;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rp.sg.GPSTrackingEntities.*;
import rp.sg.GPSTrackingRepository.DataGenerator;
/**
 *
 * @author DarkMoon
 */
public class CommonTest {

    public CommonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    @Test
    public void testLoadingGoodsToOrder()throws CommonException
    {
        //under circumstance that we try to load an good to order which is extend the limit of order(measured by weight)
        Order newOrder = new Order(1,null);
        newOrder.loadGoods(new Goods(1,"Manga",100));
        newOrder.loadGoods(new Goods(2,"Comic",200));
        newOrder.loadGoods(new Goods(3,"Mangul",300));
        //test
        //assertFalse(newOrder.loadGoods(new Goods(4,"Test Good",10000)));
        assertEquals(newOrder.getAmountOfGoods(),3);
    }
    @Test(expected=CommonException.class)
    public void testLoadingGoodsToOrderThatExceedWeightLimit()throws CommonException
    {
        //under circumstance that we try to load an good to order which is extend the limit of order(measured by weight)
        Order newOrder = new Order(1,null);
        newOrder.loadGoods(new Goods(1,"Manga",100));
        newOrder.loadGoods(new Goods(2,"Comic",200));
        newOrder.loadGoods(new Goods(3,"Mangul",300));
        newOrder.loadGoods(new Goods(4,"TV",900));
    }

    @Test(expected=NullPointerException.class)
    public void testUnloadGoodsFromOrder()throws CommonException
    {
        //before
        Order newOrder = new Order(1,null);
        Goods good1 = new Goods(1,"Manga",100);
        Goods good2 = new Goods(2,"Comic",200);
        Goods good3 = new Goods(3,"Mangul",300);
        newOrder.loadGoods(good1);
        newOrder.loadGoods(good2);
        newOrder.loadGoods(good3);
        newOrder.unloadGoods(good3);
        //test
        newOrder.unloadGoods(good3);
    }
    @Test
    public void testAddOrderToDeliveryRecord() throws CommonException
    {
        DeliveryRecord record = new DeliveryRecord(0,null,null);
        record.addOrder(new Order(1,null));
        record.addOrder(new Order(2,null));
        //test
        assertEquals(record.getAmountOfOrder(),2);
    }
    @Test(expected=CommonException.class)
    public void testAddOrderToDeliveryRecordThatExceedWeightLimit() throws CommonException
    {
        //before
        Order newOrder1 = new Order(1,null);
        Order newOrder2 = new Order(2,null);
        Order newOrder3 = new Order(3,null);
        Order newOrder4 = new Order(4,null);      
       
        newOrder1.loadGoods(new Goods(1,"",999));
        newOrder2.loadGoods(new Goods(2,"",999));
        newOrder3.loadGoods(new Goods(3,"",999));
        newOrder4.loadGoods(new Goods(4,"",999));

        DeliveryRecord newRecord = new DeliveryRecord(0,null,null);
        newRecord.addOrder(newOrder1);
        newRecord.addOrder(newOrder2);
        newRecord.addOrder(newOrder3);
        //test
        newRecord.addOrder(newOrder4);
    }
    @Test
    public void testCreateNewVehicleObjectWithNewestLocation()
    {
        //before
        Vehicle newVehicle = new Vehicle(1);
        GeoPoint newGeoPoint = new GeoPoint("1.4399679940455","103.78843203979");
        GeoPoint newestGeoPoint = new GeoPoint("1000","1000");
        newVehicle.trackLocation(newGeoPoint);
        newVehicle.trackLocation(newestGeoPoint);
        //test
        assertEquals(newestGeoPoint,newVehicle.retrieveCurrentLocation());
    }
    @Test
    public void testFakeDataGenerator()
    {
        //before
        DataGenerator dg = new DataGenerator();
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
        vehicleList = dg.generateFakeVehicleWithLoc();
        System.out.print(vehicleList.toString());
        //test
        assertEquals(vehicleList.size(),2);
    }
}