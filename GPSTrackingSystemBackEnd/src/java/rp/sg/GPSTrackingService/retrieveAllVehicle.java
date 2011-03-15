/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTrackingService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rp.sg.GPSTrackingEntities.*;
import rp.sg.GPSTrackingDatabase.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
/**
 *
 * @author NGUYEN TUAN VIET
 */
public class retrieveAllVehicle extends HttpServlet {
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
        ArrayList<Vehicle> busyVehicleList = new ArrayList<Vehicle>();
        vehicleList = dbAdapter.retrieveVehicleList();
        for(DeliveryRecord record:dbAdapter.retrieveDeliveryRecordList()){
            if(record.isAllOrderDelivered()==false){
                busyVehicleList.add(record.getVehicle());
            }
        }
        dbAdapter.closeDatabase();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode rootNode = objectMapper.createObjectNode();
            ArrayNode parentNode = rootNode.putArray("AllVehicle");
            for (Vehicle vehicle : vehicleList) {
                ObjectNode driverInfoNode = parentNode.addObject();
                driverInfoNode.put("vehicleId", vehicle.getVehicleID());
                driverInfoNode.put("currentLocation", vehicle.retrieveCurrentLocation().getLat()+","+vehicle.retrieveCurrentLocation().getLng());
                String status = "available";
                for(Vehicle v:busyVehicleList){
                    if(v.equals(vehicle)){
                        status = "hired";
                        break;
                    }
                }
                driverInfoNode.put("status", status);
            }
            out.print(rootNode);
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
