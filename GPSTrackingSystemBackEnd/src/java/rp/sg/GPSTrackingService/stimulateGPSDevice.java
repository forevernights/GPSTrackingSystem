/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTrackingService;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rp.sg.GPSTrackingDatabase.*;
import rp.sg.GPSTrackingEntities.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import java.util.ArrayList;
/**
 *
 * @author Nguyen Tuan Viet
 */
public class stimulateGPSDevice extends HttpServlet {   
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int idVehicle = Integer.parseInt(request.getParameter("idVehicle"));
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        Vehicle vehicle = dbAdapter.findVehicleById(idVehicle);
        ArrayList<GeoPoint> locationList = new ArrayList<GeoPoint>();
        locationList = vehicle.getLocationHistory();
        dbAdapter.closeDatabase();
        PrintWriter out = response.getWriter();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode rootNode = objectMapper.createObjectNode();
            ArrayNode parentNode = rootNode.putArray("Vehicle");
            for(GeoPoint location:locationList){
                ObjectNode vehicleInfoNode = parentNode.addObject();
                vehicleInfoNode.put("lat", location.getLat());
                vehicleInfoNode.put("lng", location.getLng());
            }
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
