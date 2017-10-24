package com.example.monique.hrpaknjiga.models;

public class NearbyPlacesInfo {
    private NearbyPlace[] mNearbyPlaces;

    public NearbyPlacesInfo() {

    }

    public NearbyPlacesInfo(NearbyPlace[] mNearbyPlaces) {
        this.mNearbyPlaces = mNearbyPlaces;
    }

    public NearbyPlace[] getmNearbyPlaces() {
        return mNearbyPlaces;
    }

    public void setmNearbyPlaces(NearbyPlace[] mNearbyPlaces) {
        this.mNearbyPlaces = mNearbyPlaces;
    }

    public int getLength() {
        return mNearbyPlaces.length;
    }
}
