package com.example.Model;

/**
 * Created by Junaid on 3/9/2019.
 */

public class Location {
    String siteID;
    String businessDescription;
    Boolean hasClasses;
    String ID;
    String name;

    public Location() {

    }
    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public Boolean getHasClasses() {
        return hasClasses;
    }

    public void setHasClasses(Boolean hasClasses) {
        this.hasClasses = hasClasses;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
