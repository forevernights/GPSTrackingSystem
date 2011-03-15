

package rp.sg.GPSTrackingEntities;


public class Goods {
    int id;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Goods other = (Goods) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }
    String name;
    double weight;

    public Goods(int id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Goods (){

    }

    public int getGoodsId() {
        return id;
    }

    public void setGoodsId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return name;
    }

    public void setGoodsName(String name) {
        this.name = name;
    }

    public double getGoodsWeight() {
        return weight;
    }

    public void setGoodsWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Goods{" + " id= " + id + "; name= " + name + "; weight= " + weight + "kg" +'}';
    }

    
}
