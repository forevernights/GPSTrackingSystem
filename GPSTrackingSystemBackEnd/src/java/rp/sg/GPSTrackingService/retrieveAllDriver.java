/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rp.sg.GPSTrackingService;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import rp.sg.GPSTrackingEntities.*;
import rp.sg.GPSTrackingDatabase.*;

/**
 *
 * @author NGUYEN TUAN VIET
 */
public class retrieveAllDriver extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        ArrayList<Driver> driverList = new ArrayList<Driver>();
        ArrayList<Driver> busyDriverList = new ArrayList<Driver>();
        driverList = dbAdapter.retrieveDriverList();
        for(DeliveryRecord record:dbAdapter.retrieveDeliveryRecordList()){
            if(record.isAllOrderDelivered()==false){
                busyDriverList.add(record.getDriver());
            }
        }
        dbAdapter.closeDatabase();
        try {           
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode rootNode = objectMapper.createObjectNode();
            ArrayNode parentNode = rootNode.putArray("AllDriver");
            for (Driver driver : driverList) {
                ObjectNode driverInfoNode = parentNode.addObject();
                driverInfoNode.put("driverId", driver.getDriverId());
                driverInfoNode.put("driverName", driver.getDriverName());
                String status = "available";
                for(Driver d:busyDriverList){
                    if(driver.equals(d)){
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
