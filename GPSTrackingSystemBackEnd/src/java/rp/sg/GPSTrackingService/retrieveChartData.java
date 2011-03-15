/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTrackingService;

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
public class retrieveChartData extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        ArrayList<DeliveryRecord> recordList = new ArrayList<DeliveryRecord>();
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
        recordList = dbAdapter.retrieveDeliveryRecordList();
        vehicleList = dbAdapter.retrieveVehicleList();
        dbAdapter.closeDatabase();
        PrintWriter out = response.getWriter();
        try {            
            out.println("<chart caption='Number of delivers on each vehicle' subcaption='' " +
                    "xAxisName='Vehicle' yAxisName='Number of delivers'> ");
            for(Vehicle vehicle:vehicleList){
                int count = 0;
                for(DeliveryRecord record:recordList){
                    if(record.getVehicle().equals(vehicle)){
                        count++;
                    }
                }
                out.println("<set label='"+vehicle.getVehicleID()+"' value='"+count+"' /> ");
            }
            out.println("</chart>");            
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
    }// </editor-fold>

}
