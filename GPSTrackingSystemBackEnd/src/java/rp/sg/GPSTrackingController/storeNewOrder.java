/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTrackingController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.sym.Name1;

import rp.sg.GPSTrackingDatabase.*;
import rp.sg.GPSTrackingEntities.*;

/**
 *
 * @author Nguyen Tuan Viet
 */
public class storeNewOrder extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        String name = request.getParameter("name");
        int weight = Integer.parseInt(request.getParameter("weight"));
        GeoPoint dropPoint = new GeoPoint(lat,lng);
        PrintWriter out = response.getWriter();
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this.getServletContext());
        Goods good = new Goods(dbAdapter.retrieveGoodsList().size()+1,name,weight);
        Order newOrder = new Order(dbAdapter.retrieveOrderList().size()+1, dropPoint);
        try {
            newOrder.loadGoods(good);
        } catch (CommonException ex) {
            Logger.getLogger(storeNewOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbAdapter.storeOrder(newOrder);
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
