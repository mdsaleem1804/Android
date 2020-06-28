package com.hellotamila.ah_and_009_hospial;

public class SimpleGetterAndSetter {
    private static  String  username;
    private static  String  doctor;
    private static  String  medlat;
    private static  String  medlong;


    public void setUserName(String xUserName ) {
        this.username = xUserName;
    }
    public String getUserName() {
        return username;
    }
    public void setDoctor(String xDoctor ) {
        this.doctor = xDoctor;
    }
    public String getDoctor() {
        return doctor;
    }
    public void setMedLatLong(String xLat,String xLong ) {
        this.medlat = xLat;
        this.medlong = xLong;
    }
    public String getMedLat() {
        return medlat;
    }
    public String getMedLong() {
        return medlong;
    }
}
