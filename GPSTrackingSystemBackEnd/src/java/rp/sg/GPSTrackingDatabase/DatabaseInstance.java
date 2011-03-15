




package rp.sg.GPSTrackingDatabase;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import javax.servlet.ServletContext;

public class DatabaseInstance {
    private static String DATA_PATH = "NEWDATABASE";
    private ObjectContainer db;
    private ServletContext context;
    public DatabaseInstance(){}
    public ObjectContainer getDatabase(ServletContext context){
        this.context = context;
        db = (ObjectContainer) Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), 
                this.context.getRealPath("/"+DATA_PATH));
        return db;
    }    
}
