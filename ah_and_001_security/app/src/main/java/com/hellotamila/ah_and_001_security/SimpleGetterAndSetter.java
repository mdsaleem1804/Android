package com.hellotamila.ah_and_001_security;

public class SimpleGetterAndSetter {
    private static  String  username;
    private static String mobileno;
    private static  String imei;
    private static  String image1;
    private static  String image2;
    private static  String otp;
    public String getUsername() {
        return this.username;
    }
    public String getMobileno() {
        return mobileno;
    }
    public String getImei() {
        return imei;
    }
    public String getImage1() {
        return image1;
    }
    public String getImage2() {
        return image2;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    public String getOtp() {
        return otp;
    }
    public void setDetails(String a, String b) {
        this.username = a;
        this.mobileno = b;

    }
    public void setMobileNo(String mobileno) {
        this.mobileno = mobileno;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public void setImage1(String image1) {
        this.image1 = image1;
    }
    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
