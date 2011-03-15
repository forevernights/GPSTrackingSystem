/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTrackingDatabase;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rp.sg.GPSTrackingEntities.*;


/**
 *
 * @author NguyenTuanViet
 */
public class GenerateQuery {
    public static void main(String[] args) {
        recoverDatabase();
    }
    private static void recoverDatabase(){
        DatabaseAdapter dbAdapter = new DatabaseAdapter(null);
        ArrayList<Order> orderList = new ArrayList<Order>();
        orderList = dbAdapter.retrieveOrderList();
        for(Order order:orderList){
            dbAdapter.storeOrder(order);
        }
        dbAdapter.closeDatabase();
    }
}
