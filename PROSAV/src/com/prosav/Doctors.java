package com.prosav;

public class Doctors {
    
    private int id;
    private String name;
     
    public Doctors(){}
     
    public Doctors(String name){
     
        this.name = name;
    }
     
    public void setId(int id){
        this.id = id;
    }
     
    public void setName(String name){
        this.name = name;
    }
     
    public int getId(){
        return this.id;
    }
     
    public String getName(){
        return this.name;
    }
 
}