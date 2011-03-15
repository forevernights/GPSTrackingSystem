
package rp.sg.GPSTrackingEntities;

public class Driver {
    String name;
    int id;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Driver other = (Driver) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 17 * hash + this.id;
        return hash;
    }

    public Driver() {
    }

    public Driver(int id, String name) {
        this.name = name;
        this.id = id;
    }
    
    public int getDriverId() {
        return id;
    }

    public void setDriverId(int id) {
        this.id = id;
    }

    public String getDriverName() {
        return name;
    }

    public void setDriverName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Driver{" + " id = " + id +"; name = " + name + '}';
    }

}
