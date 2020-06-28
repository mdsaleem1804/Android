package www.votingsystem;

public class SimpleGetterAndSetter {
    private static String name;
    private static String uid;
    private static String gender;
    private static String yob;
    private static String barcode;
    private static String otp;
    private static String mobile;
    public String a() {
        return this.name;
    }
    public String b() {
        return uid;
    }
    public String c() {
        return gender;
    }
    public String d() {
        return yob;
    }
    public String e() {
        return barcode;
    }
    public String f() {
        return otp;
    }
    public String g() {
        return mobile;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
    public void setDetails(String a, String b, String c, String d, String e, String f, String g ) {
        this.name = a;
        this.uid = b;
        this.gender = c;
        this.yob = d;
        this.barcode = e;
        this.otp =f;
        this.mobile =g;
    }

}
