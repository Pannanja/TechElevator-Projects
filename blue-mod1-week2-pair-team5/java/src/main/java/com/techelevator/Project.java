package com.techelevator;
import java.util.List;
import java.util.ArrayList;

public class Project {
    private String name;
    private String description;
    private String startDate;
    private String dueDate;
    private List<Employee> teamMembers = new ArrayList<Employee>();

    public Project(String name, String description, String startDate, String dueDate){
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public void setName(String name) { this.name = name;}
    public String getName(){return name;}

    public void setDescription(String description){this.description = description;}
    public String getDescription(){return description;}

    public void setStartDate(String startDate){this.startDate = startDate;}
    public String getStartDate(){return startDate;}

    public void setDueDate(String dueDate){this.dueDate = dueDate;}
    public String getDueDate(){return dueDate;}

    public void setTeamMembers(List<Employee> teamMembers){
        this.teamMembers = teamMembers;
    }
    public List<Employee> getTeamMembers(){return teamMembers;}
}
