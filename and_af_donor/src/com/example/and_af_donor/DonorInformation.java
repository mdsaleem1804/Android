package com.example.and_af_donor;



public class DonorInformation {

    int id;
    String name,email,phone , bloodGroup, lastDate,addressline1,addressline2;

    public DonorInformation(int id, String name, String email, String phone, String bloodGroup, String lastDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.lastDate = lastDate;

    }

    public DonorInformation(int id, String name, String email, String phone, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
    }

    public DonorInformation(String name, String email, String phone, String bloodGroup) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
    }

    public DonorInformation(String name, String email, String phone, String bloodGroup, String lastDate,String AddressLine1,String AddressLine2) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.lastDate = lastDate;
        this.addressline1=AddressLine1;
        this.addressline2=AddressLine2;
    }
}
