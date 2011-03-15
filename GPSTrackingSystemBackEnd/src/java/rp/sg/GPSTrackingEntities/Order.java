

package rp.sg.GPSTrackingEntities;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private ArrayList<Goods> goodsList;
    private GeoPoint dropPoint;
    private Boolean isDelivered;
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        if (this.goodsList != other.goodsList && (this.goodsList == null || !this.goodsList.equals(other.goodsList))) {
            return false;
        }
        if (this.dropPoint != other.dropPoint && (this.dropPoint == null || !this.dropPoint.equals(other.dropPoint))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.orderID;
        hash = 37 * hash + (this.goodsList != null ? this.goodsList.hashCode() : 0);
        hash = 37 * hash + (this.dropPoint != null ? this.dropPoint.hashCode() : 0);
        return hash;
    }
    

    public Order(int orderID, GeoPoint dropPoint) {
        this.orderID = orderID;
        goodsList = new ArrayList<Goods>();
        this.dropPoint = dropPoint;
        isDelivered = false;
    }

    public GeoPoint getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(GeoPoint dropPoint) {
        this.dropPoint = dropPoint;
    }  

    public void setGoodsList(ArrayList<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String toString(){
        return "Order {" + "id = "+ orderID + "; Goods = " + goodsList + "; Drop point = " + dropPoint;
    }
    public Boolean returnDeliverStat(){
        return this.isDelivered;
    }
    public void deliverOrder(){
        this.isDelivered = true;
    }
    public void unDoDeliver(){
        this.isDelivered = false;
    }
    //load cargo to vehicle
    public void loadGoods(Goods good) throws CommonException
    {
        if(this.checkValidWeightGoods(good)==true)
        {
            goodsList.add(good);            
        }
        else
        {
            throw new CommonException("LimitedWeight");
        }
    }
    //unload cargo to vehicle
    public boolean unloadGoods(Goods good) throws NullPointerException
    {
        Boolean isGoodExist = false;
        for(Goods g:goodsList)
        {
            if(g == good)
            {                
                isGoodExist = true;
            }            
        }
        if(isGoodExist == true)
        {
            goodsList.remove(good);
        }
        else
        {
            throw new NullPointerException();
        }
        return isGoodExist;
    }
    //to check the total weight of the whole inventory
    private boolean checkValidWeightGoods(Goods good)
    {
        double currentWeight = 0;
        double totalWeight =0;
        for(Goods g:goodsList)
        {
            currentWeight += g.getGoodsWeight();
        }
        totalWeight = currentWeight + good.getGoodsWeight();
        if(totalWeight>=1000)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    //terminate order, unload every cargoes inside inventory
    public void cancelOrder()
    {
        goodsList.removeAll(goodsList);
    }
    public double getTotalWeightOrder()
    {
        double totalWeight = 0;
        for(Goods g:goodsList)
        {
            totalWeight += g.getGoodsWeight();
        }
        return totalWeight;
    }
    public int getAmountOfGoods()
    {
        return goodsList.size();
    }
}
