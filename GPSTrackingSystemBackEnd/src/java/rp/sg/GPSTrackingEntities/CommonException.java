/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rp.sg.GPSTrackingEntities;

/**
 *
 * @author DarkMoon
 */
public class CommonException extends Exception {
    String error;
    public CommonException()
    {
        super();
        error = "unknown";
    }
    public CommonException(String error)
    {
        super(error);
        this.error = error;
    }
    public String getError()
    {
        return error;
    }
}
