

package rp.sg.GPSTrackingEntities;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rp.sg.GPSTrackingDatabase.*;


public class Main {
public static void main(String[] args) {
    DatabaseAdapter adapter= new DatabaseAdapter(null);
   
 
   /*
    Goods goods1 = new Goods(1,"Moew",1);
    Goods goods2 = new Goods(2,"Dog",2);
    Goods goods3 = new Goods(3,"Mouse",3);
    Goods goods4 = new Goods(4,"Chix",4);
    Goods goods5 = new Goods(5,"Duck",5);
    Goods goods6 = new Goods(6,"Swan",6);
    Goods goods7 = new Goods(7,"T-rex",7);
    Goods goods8 = new Goods(8,"Bird",8);
    Goods goods9 = new Goods(9,"Flies",9);
    Goods goods10 = new Goods(10,"Cockroach",10);

    GeoPoint geoPoint1 = new GeoPoint("1.4335662226731052 ", "103.78698348999023");
    GeoPoint geoPoint2 = new GeoPoint("1.290269501929712", "103.8401985168457");
    GeoPoint geoPoint3 = new GeoPoint("1.295675458510715", "103.83238792419434");
    GeoPoint geoPoint4 = new GeoPoint("1.3005236478557476", "103.83045673370361");
    GeoPoint geoPoint5 = new GeoPoint("1.3039130844347167", "103.83283853530884");
    GeoPoint geoPoint6 = new GeoPoint("1.3082893122529637", "103.8327956199646");
    GeoPoint geoPoint7 = new GeoPoint("1.330942604240713", "103.85032653808594");
    GeoPoint geoPoint8 = new GeoPoint("1.3371207388169672", "103.90405654907227");
    GeoPoint geoPoint9 = new GeoPoint("1.3106919438892097", "103.88620376586914");
    GeoPoint geoPoint10 = new GeoPoint("1.4486676456977465", "103.8153076171875");
    
    Order order1 = new Order(1, geoPoint1);
       try{
    order1.loadGoods(goods1);
    order1.loadGoods(goods2);
    }
    catch(CommonException cm){}

    Order order2 = new Order(2, geoPoint3);
       try{
    order2.loadGoods(goods3);
    order2.loadGoods(goods4);
    }
    catch(CommonException cm){}

    Order order3 = new Order(3, geoPoint5);
       try{
    order3.loadGoods(goods5);
    order3.loadGoods(goods6);
    }
    catch(CommonException cm){}

    Order order4 = new Order(4, geoPoint7);
       try{
    order4.loadGoods(goods7);
    order4.loadGoods(goods8);
    }
    catch(CommonException cm){}

    Order order5 = new Order(5, geoPoint9);
       try{
    order5.loadGoods(goods9);
    order5.loadGoods(goods10);
    }
    catch(CommonException cm){}

    Order order6 = new Order(6, geoPoint2);
       try{
    order6.loadGoods(goods1);
    order6.loadGoods(goods3);
    }
    catch(CommonException cm){}

    Order order7 = new Order(7, geoPoint4);
       try{
    order7.loadGoods(goods2);
    order7.loadGoods(goods4);
    }
    catch(CommonException cm){}

    Order order8 = new Order(8, geoPoint6);
       try{
    order8.loadGoods(goods5);
    order8.loadGoods(goods7);
    }
    catch(CommonException cm){}

    Order order9 = new Order(9, geoPoint8);
       try{
    order9.loadGoods(goods6);
    order9.loadGoods(goods8);
    }
    catch(CommonException cm){}

    Vehicle vehicle1 = new Vehicle(1);
    vehicle1.trackLocation(geoPoint1);
    vehicle1.trackLocation(geoPoint2);

    Vehicle vehicle2 = new Vehicle(2);
    vehicle2.trackLocation(geoPoint3);
    vehicle2.trackLocation(geoPoint4);

    Vehicle vehicle3 = new Vehicle(3);
    vehicle3.trackLocation(geoPoint5);
    vehicle3.trackLocation(geoPoint6);

    Vehicle vehicle4 = new Vehicle(4);
    vehicle4.trackLocation(geoPoint7);
    vehicle4.trackLocation(geoPoint8);

    Vehicle vehicle5 = new Vehicle(5);
    vehicle5.trackLocation(geoPoint9);
    vehicle5.trackLocation(geoPoint10);

    Driver driver1 = new Driver(1,"Susan");
    Driver driver2 = new Driver(2,"Tonie");
    Driver driver3 = new Driver(3,"Viet");
    Driver driver4 = new Driver(4,"Gao");
    Driver driver5 = new Driver(5,"TIffany");

    DeliveryRecord deliveryRecord1 = new DeliveryRecord(1, driver1, vehicle1);
     try {
            deliveryRecord1.addOrder(order1);
            deliveryRecord1.addOrder(order9);
        } catch (CommonException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    adapter.storeDeliveryRecord(deliveryRecord1);

    DeliveryRecord deliveryRecord2 = new DeliveryRecord(2, driver2, vehicle2);
     try {
            deliveryRecord2.addOrder(order2);
            deliveryRecord2.addOrder(order8);
        } catch (CommonException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    adapter.storeDeliveryRecord(deliveryRecord2);

    DeliveryRecord deliveryRecord3 = new DeliveryRecord(3, driver3, vehicle3);
     try {
            deliveryRecord3.addOrder(order3);
            deliveryRecord3.addOrder(order7);
        } catch (CommonException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    adapter.storeDeliveryRecord(deliveryRecord3);

    DeliveryRecord deliveryRecord4 = new DeliveryRecord(4, driver4, vehicle4);
     try {
            deliveryRecord4.addOrder(order4);
            deliveryRecord4.addOrder(order6);
        } catch (CommonException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    adapter.storeDeliveryRecord(deliveryRecord4);

    DeliveryRecord deliveryRecord5 = new DeliveryRecord(5, driver5, vehicle5);
     try {
            deliveryRecord5.addOrder(order5);
        } catch (CommonException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    adapter.storeDeliveryRecord(deliveryRecord5);

*/
    
    DeliveryRecord deliveryRecord = adapter.findDeliveryRecord(1, null, null);
    System.out.println(deliveryRecord);
    ArrayList<DeliveryRecord> deliveryRecordList = adapter.retrieveDeliveryRecordList();
    for(DeliveryRecord dr:deliveryRecordList){
    System.out.println(dr.toString());
    }
   
}
}