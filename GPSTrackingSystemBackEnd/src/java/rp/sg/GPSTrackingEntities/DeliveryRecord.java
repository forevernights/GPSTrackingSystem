

package rp.sg.GPSTrackingEntities;
import java.util.ArrayList;


public class DeliveryRecord {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DeliveryRecord other = (DeliveryRecord) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.driver != other.driver && (this.driver == null || !this.driver.equals(other.driver))) {
            return false;
        }
        if (this.vehicle != other.vehicle && (this.vehicle == null || !this.vehicle.equals(other.vehicle))) {
            return false;
        }
        if (this.orderList != other.orderList && (this.orderList == null || !this.orderList.equals(other.orderList))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + (this.driver != null ? this.driver.hashCode() : 0);
        hash = 61 * hash + (this.vehicle != null ? this.vehicle.hashCode() : 0);
        hash = 61 * hash + (this.orderList != null ? this.orderList.hashCode() : 0);
        return hash;
    }

    private int id;
    private Driver driver;
    private Vehicle vehicle;
    private ArrayList<Order> orderList;
   

  
    public DeliveryRecord(int id,Driver driver, Vehicle vehicle) {
        this.id = id;
        this.driver = driver;
        this.vehicle = vehicle;
        orderList = new ArrayList<Order>();      
    }


    public int getId() {
        return id;

    }


    public void setId(int id) {
        this.id = id;

    }



    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
  
    
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    
      public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "DeliveryRecord{"+ driver + "; " + vehicle + "; OrderList=" + orderList + '}';
    }

    public void addOrder(Order order) throws CommonException
    {
        double currentWeight = 0;
        double totalWeight = 0;
        if(orderList.isEmpty()){
            orderList.add(order);
        }
        else{
            for(int i = 0; i < orderList.size(); i++)
            {
                currentWeight += orderList.get(i).getTotalWeightOrder();
            }
            totalWeight = currentWeight + order.getTotalWeightOrder();
            if(totalWeight<3000)
            {
                orderList.add(order);
            }
            else
            {
                throw new CommonException("NoOrderAllowed");
            }
        }
    }
    public int getAmountOfOrder()
    {
        return orderList.size();
    }
    public boolean isAllOrderDelivered(){
        Boolean isAllDelivered = true;
        for(Order o:orderList){
            if(o.returnDeliverStat()==false){
                isAllDelivered = false;
                break;
            }
        }
        return isAllDelivered;
    }
}

