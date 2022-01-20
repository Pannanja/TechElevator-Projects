package com.techelevator;

public class Department {
    private int departmentId = 0;
    private String name = null;

    public Department(int departmentId, String name){
        this.departmentId = departmentId;
        this.name = name;
    }

    public int getDepartmentID(){
        return departmentId;
    }
    public void setDepartmentID(int departmentID){
        this.departmentId = departmentID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
