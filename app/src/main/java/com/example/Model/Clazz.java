package com.example.Model;

import com.example.Utils.Constants;

/**
 * Created by Junaid on 3/9/2019.
 */

public class Clazz {
    String classScheduleID;
    Location location;
    String maxCapacity;
    String webCapacity;
    String totalBooked;
    String totalBookedWaitlist;
    String webBooked;
    Boolean isCanceled;
    Boolean active;
    Boolean isEnrolled;
    Boolean isWaitlistAvailable;
    String ID;
    Boolean isAvailable;
    String startDateTime;
    String endDateTime;

    public Clazz() {
    }

    public String getClassScheduleID() {
        return classScheduleID;
    }

    public void setClassScheduleID(String classScheduleID) {
        this.classScheduleID = classScheduleID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getWebCapacity() {
        return webCapacity;
    }

    public void setWebCapacity(String webCapacity) {
        this.webCapacity = webCapacity;
    }

    public String getTotalBooked() {
        return totalBooked;
    }

    public void setTotalBooked(String totalBooked) {
        this.totalBooked = totalBooked;
    }

    public String getTotalBookedWaitlist() {
        return totalBookedWaitlist;
    }

    public void setTotalBookedWaitlist(String totalBookedWaitlist) {
        this.totalBookedWaitlist = totalBookedWaitlist;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getWaitlistAvailable() {
        return isWaitlistAvailable;
    }

    public void setWaitlistAvailable(Boolean waitlistAvailable) {
        isWaitlistAvailable = waitlistAvailable;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getWebBooked() {
        return webBooked;
    }

    public void setWebBooked(String webBooked) {
        this.webBooked = webBooked;
    }

    public Boolean getEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(Boolean enrolled) {
        isEnrolled = enrolled;
    }


}
