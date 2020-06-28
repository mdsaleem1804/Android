package com.hellotamila.ah_and_011_book;

public class SimpleGetterAndSetter {
    private static String rollno;
    private static String name;
    private static String staffname;
    private static String xSesionStudent="No";


    private static String remarks;
    public String getRollno() {
        return rollno;
    }
    public String getName() {
        return name;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRollno(String rollno) {
        this.rollno = rollno;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public void setSessionStudent(String xSesionStudent) {
        this.xSesionStudent = xSesionStudent;
    }
    public String getSessionStudent() {
        return xSesionStudent;
    }

    public void setStaffName(String staffname) {
        this.staffname = staffname;
    }
    public String getStaffName() {
        return staffname;
    }
}
