package com.example.tadi.Model;

import java.io.Serializable;

public class Photo implements Serializable {

    private int resourceID;

    public Photo() {
    }

    public Photo(int resourceID) {
        this.resourceID = resourceID;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }
}
