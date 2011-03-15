/*
 * author NGUYEN TUAN VIET
 * the controller to store current location of vehicle to JSON file.
 */
package rp.sg.GPSTrackingService;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rp.sg.GPSTrackingDatabase.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import rp.sg.GPSTrackingEntities.*;
import rp.sg.GPSTrackingRepository.*;
public class locationTracking extends HttpServlet {
    String filePath;
    int vehicleId;
    GeoPoint currentLoc;
    GeoPoint dropPointLoc;
    DataGenerator dg;  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        ArrayList<DeliveryRecord> recordList = new ArrayList<DeliveryRecord>();
        recordList = dbAdapter.retrieveDeliveryRecordList();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        ArrayNode parentNode = rootNode.putArray("TrackingAllVehicleLocation");
        for(DeliveryRecord record:recordList){
            if(record.isAllOrderDelivered()==false){
                Vehicle vehicle = record.getVehicle();
                ObjectNode vehicleInfoNode = parentNode.addObject();
                vehicleInfoNode.put("vehicleId", vehicle.getVehicleID());
                ObjectNode currentLocNode = vehicleInfoNode.putObject("currentLoc");
                currentLocNode.put("lat", vehicle.retrieveCurrentLocation().getLat());
                currentLocNode.put("lng", vehicle.retrieveCurrentLocation().getLng());
                ArrayNode dropPointNode = vehicleInfoNode.putArray("dropPoint");
                for(Order order:record.getOrderList()){
                    ObjectNode dropPointInfoNode = dropPointNode.addObject();
                    dropPointInfoNode.put("lat", order.getDropPoint().getLat());
                    dropPointInfoNode.put("lng", order.getDropPoint().getLng());
                }                
            }
        }
        dbAdapter.closeDatabase();
        try {
            out.print(rootNode);
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
