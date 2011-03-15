/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rp.sg.GPSTrackingController;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.TreeMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import rp.sg.GPSTrackingEntities.*;
/**
 *
 * @author DarkMoon
 */
public class locationTrackingJackson extends HttpServlet {
    String filePath;
    String vehicleID;
    String lng;
    String lat;
    Vehicle vehicle;
   
    public void init() throws ServletException {
        filePath = "C:/Users/DarkMoon/Desktop/FYP/gpstracking88/GPSTrackingSystemGoogle/web/locationHistory.json";
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        lng = request.getParameter("lng");
        lat = request.getParameter("lat");
        vehicleID = request.getParameter("vehicleID");

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createJsonParser(new File(filePath));
            JsonGenerator g = jsonFactory.createJsonGenerator(new File(filePath), JsonEncoding.UTF8);
            g.useDefaultPrettyPrinter();
            //Object ArrayList locationHistory to store all vehicle locations
            ArrayList<GeoPoint> locationHistory = new ArrayList<GeoPoint>();
            JsonNode rootNode = mapper.readTree(jsonParser);
            
            //out.print(rootNode.get("1").get(1).get("lng"));
            //out.print(rootNode.get("1").get(2));
            JsonNode arrayNode = rootNode.path(vehicleID);
            if(arrayNode.isMissingNode()==false)
            {
                ObjectNode locationNode = ((ArrayNode) arrayNode).addObject();
                locationNode.put("lng", lng);
                locationNode.put("lat", lat);
            }
            else
            {
                TreeMapper treeMapper = new TreeMapper();                
                ArrayNode newArrayNode = treeMapper.arrayNode();
                ObjectNode locationNode = ((ArrayNode) newArrayNode).addObject();
                locationNode.put("lng", lng);
                locationNode.put("lat", lat);
                ((ObjectNode)rootNode).put(vehicleID, newArrayNode);
            }
            mapper.writeValue(g, rootNode);
           
            for(JsonNode childNode : rootNode)
            {               
                
                out.print("<br/>");
                for(JsonNode grandChildNode:childNode)
                {                    
                    out.print("+");
                    out.print(grandChildNode.get("lng").getTextValue());
                    out.print(",");
                    out.print(grandChildNode.get("lat").getTextValue());
                    out.print("<br/>");
                    //declare new GeoPoint variable as current location of vehicle that send GPS signal
                    GeoPoint geoPoint = new GeoPoint(grandChildNode.get("lng").getTextValue(),grandChildNode.get("lat").getTextValue());
                    locationHistory.add(geoPoint);
                }
            }
            //declare Vehicle class with vehicleID and recorded location<GeoPoint>          
            out.print(locationHistory);
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
