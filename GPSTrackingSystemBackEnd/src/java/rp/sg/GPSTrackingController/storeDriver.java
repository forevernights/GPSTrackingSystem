package rp.sg.GPSTrackingController;

import java.io.IOException;
import java.io.PrintWriter;
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
public class storeDriver extends HttpServlet {
    private int driverId = 0;
    private String driverName = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /*DatabaseAdapter dbAdapter = new DatabaseAdapter();
        driverId = Integer.parseInt(request.getParameter("id"));
        driverName = request.getParameter("name");
        Driver newDriver = new Driver(driverId,driverName);*/
        try {
            out.print(this.getServletContext().getRealPath("/NEWDATABASE"));
            /*dbAdapter.storeDriver(newDriver);
            out.print("Driver stored successfully");
            dbAdapter.closeDatabase();*/
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
