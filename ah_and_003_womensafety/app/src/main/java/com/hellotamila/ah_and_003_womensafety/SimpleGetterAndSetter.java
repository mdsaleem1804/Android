package com.hellotamila.ah_and_003_womensafety;

public class SimpleGetterAndSetter {
    private static  String  lowlat;
    private static String lowlong;
    private static  String lowmob;
    private static  String highlat;
    private static  String highlong;
    private static  String highmob;
    public String a() {
        return this.lowlat;
    }
    public String b() {
        return lowlong;
    }
    public String c() {
        return lowmob;
    }
    public String d() {
        return highlat;
    }
    public String e() {
        return highlong;
    }
    public String f() {
        return highmob;
    }

    public void setDetails(String a, String b,String c,String d, String e,String f ) {
        this.lowlat = a;
        this.lowlong = b;
        this.lowmob = c;
        this.highlat = d;
        this.highlong = e;
        this.highmob =f;
    }
    public void setLowlat(String a, String b,String c ) {
        this.lowlat = a;
        this.lowlong = b;
        this.lowmob = c;

    }
    public void setHighlat(String d, String e,String f ) {
        this.highlat = d;
        this.highlong = e;
        this.highmob =f;
    }
}
