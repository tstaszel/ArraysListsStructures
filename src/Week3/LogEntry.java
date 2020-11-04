package Week3;
import java.util.Date;

public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    //constructor
    public LogEntry(String ip,Date time,String req,int status,int bytes){
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }

    //getters
    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesReturned() {
        return bytesReturned;
    }

    public String toString() {
        return ipAddress + "" + accessTime + "" + request +
                " " + statusCode + " " + bytesReturned;
    }



}
