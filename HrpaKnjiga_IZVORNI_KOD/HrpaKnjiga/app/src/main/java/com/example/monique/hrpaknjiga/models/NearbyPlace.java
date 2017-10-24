package com.example.monique.hrpaknjiga.models;

import com.example.monique.hrpaknjiga.constants.Constants;
import com.google.android.gms.maps.model.LatLng;

public class NearbyPlace {

    private String mId;
    private String mPlaceId;
    private String mType;
    private String mPlaceName;
    private double mLatitude;
    private double mLongitude;
    private String mPlaceVicinity;
    private String mPlaceIcon; //za slučaj proširenja aplikacije za prikaz ikonice, isto se može napraviti i sa slikom

    public NearbyPlace() {
        this.mId = "";
        this.mPlaceId = "";
        this.mType = "";
        this.mPlaceName = "";
        this.mLatitude = 0;
        this.mLongitude = 0;
        this.mPlaceVicinity = "";
        this.mPlaceIcon = "";

    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(String placeId) {
        this.mPlaceId = placeId;
    }

    public String getType() {
        if (mType.equals(Constants.TYPE_BOOKSTORE)) {
            return "Knjižara";
        } else if (mType.equals(Constants.TYPE_LIBRARY)) {
            return "Knjižnica";
        } else {
            return mType;
        }
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public void setPlaceName(String placeName) {
        this.mPlaceName = placeName;
    }


    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        this.mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public String getPlaceVicinity() {
        return mPlaceVicinity;
    }

    public void setPlaceVicinity(String placeVicinity) {
        this.mPlaceVicinity = placeVicinity;
    }

    public String getPlaceIcon() {
        return mPlaceIcon;
    }

    public void setPlaceIcon(String placeIcon) {
        this.mPlaceIcon = placeIcon;
    }

    public LatLng getLatLng () {
        LatLng latLng = new LatLng(this.mLatitude, this.mLongitude);
        return latLng;
    }
}

