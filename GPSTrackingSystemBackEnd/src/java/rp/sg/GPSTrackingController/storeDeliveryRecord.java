package rp.sg.GPSTrackingController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import customize class
import rp.sg.GPSTrackingDatabase.*;
import rp.sg.GPSTrackingEntities.*;
/**
 *
 * @author NGUYEN TUAN VIET
 */
public class storeDeliveryRecord extends HttpServlet {
    int id;
    int driverId;
    int vehicleId;
    int orderId;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        int sizeOfRecordList = dbAdapter.retrieveDeliveryRecordList().size();
        id = sizeOfRecordList + 1;
        driverId = Integer.parseInt(request.getParameter("driverId"));
        vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        orderId = Integer.parseInt(request.getParameter("orderId"));

        Driver driver = dbAdapter.findDriverById(driverId);
        Vehicle vehicle = dbAdapter.findVehicleById(vehicleId);
        Order order = dbAdapter.findOrderById(orderId);
        DeliveryRecord newRecord = new DeliveryRecord(id,driver,vehicle);
        try {
            newRecord.addOrder(order);
        } catch (CommonException ex) {
            Logger.getLogger(storeDeliveryRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbAdapter.storeDeliveryRecord(newRecord);
        dbAdapter.closeDatabase();
        try {
            
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
