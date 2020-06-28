package com.hellotamila.ah_and_001_security;

public class user_details {
    private String username;
    private String mobileno;
    private String imei;
    public user_details() {}
/*    public user_details(String a, String b,String c) {
        this.username = a;
        this.mobileno = b;
        this.imei = c;
    }*/
    public void setDetails(String a, String b,String c) {
        this.username = a;
        this.mobileno = b;
        this.imei = c;
    }

    public String getUsername() {
        return username;
    }
    public String getMobileno() {
        return mobileno;
    }
    public String getImei() {
        return imei;
    }
}
