package leon.iot_project.custom;

/**
 * Created by Leon on 2017/12/14.
 */

public class TrashCan {
    private int id;
    private int full;
    private String time;
    private double longitude;
    private double latitude;

    public TrashCan(int id, int full, String time, double longitude, double latitude) {
        this.id = id;
        this.full = full;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

