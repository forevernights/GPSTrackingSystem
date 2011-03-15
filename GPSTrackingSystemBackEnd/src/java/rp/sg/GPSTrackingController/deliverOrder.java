/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTrackingController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rp.sg.GPSTrackingDatabase.*;
import rp.sg.GPSTrackingEntities.*;
import java.util.ArrayList;
/**
 *
 * @author Nguyen Tuan Viet
 */
public class deliverOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int idVehicle = Integer.parseInt(request.getParameter("idVehicle"));
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        GeoPoint currentLoc = new GeoPoint(lat,lng);
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        ArrayList<DeliveryRecord> recordList = new ArrayList<DeliveryRecord>();
        recordList = dbAdapter.retrieveDeliveryRecordList();
        PrintWriter out = response.getWriter();
        try {
            for(DeliveryRecord record:recordList){
            if(record.isAllOrderDelivered()==false){
                if(record.getVehicle().getVehicleID()==idVehicle){
                    dbAdapter.updateLocationOfVehicle(record.getId(), currentLoc);
                    for(Order order:record.getOrderList()){
                        if(currentLoc.getLat().equals(order.getDropPoint().getLat())&&currentLoc.getLng().equals(order.getDropPoint().getLng())){
                            out.print("DELIVERED");
                            dbAdapter.deliverOrderOfDeliveryRecord(record.getId(), order.getOrderID());                            
                        }                                          
                    }
                }
            }
        }
        dbAdapter.closeDatabase();
        } finally {
            out.close();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
