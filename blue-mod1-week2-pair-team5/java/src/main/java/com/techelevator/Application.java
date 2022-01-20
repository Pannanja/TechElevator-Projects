package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private List<Department> departments = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private Map<String, Project> projects = new HashMap<String, Project>();

    /**
     * The main entry point in the application
     * @param args
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        // create some departments
        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();

        // give Angie a 10% raise, she is doing a great job!

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();
    }

    /**
     * Create departments and add them to the collection of departments
     */
    private void createDepartments() {
        Department marketing = new Department(1, "Marketing");
        departments.add(marketing);
        Department sales = new Department(2, "Sales");
        departments.add(sales);
        Department engineering = new Department(3, "Engineering");
        departments.add(engineering);
    }

    /**
     * Print out each department in the collection.
     */
    private void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");
        for(Department department : departments) {
            System.out.println(department.getName());
        }
    }

    /**
     * Create employees and add them to the collection of employees
     */
    private void createEmployees() {
        Employee deanJohnson = new Employee(1l, "Dean", "Johnson", "djohnson@teams.com", departments.get(2), "08/21/2020");
        Employee angieSmith = new Employee(2l, "Angie", "Smith", "asmith@teams.com",departments.get(2), "08/21/2020");
        Employee margaretThompson = new Employee(3l, "Margaret", "Thompson", "mthomson@teams.com", departments.get(0), "08/21/2020");
        employees.add(deanJohnson);
        employees.add(angieSmith);
        employees.add(margaretThompson);
        margaretThompson.raiseSalary(.1);
    }

    /**
     * Print out each employee in the collection.
     */
    private void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");
        for(Employee employee: employees){
            String toPrint = employee.getFullName() +" (" + employee.getSalary()/100 +") " + employee.getDepartment().getName();
            System.out.println(toPrint);
        }
    }

    /**
     * Create the 'TEams' project.
     */
    private void createTeamsProject() {
        Project teams = new Project("Teams", "Project Management Software", "10/10/2020", "11/10/2020");
        List<Employee> engineers = new ArrayList<>();
        for (Employee employee : employees) {
            if(employee.getDepartment().getName().equals("Engineering")) {
                engineers.add(employee);
            }
        }
        teams.setTeamMembers(engineers);
        projects.put(teams.getName(), teams);


    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private void createLandingPageProject() {
        Project marketingLandingPage = new Project("Marketing Landing Page", "Lead Capture Landing Page for Marketing", "10/10/2020", "10/17/2020");
        List<Employee> marketing = new ArrayList<>();
        for (Employee employee : employees) {
            if(employee.getDepartment().getName().equals("Marketing")){
                marketing.add(employee);
            }
        }
        marketingLandingPage.setTeamMembers(marketing);
        projects.put(marketingLandingPage.getName(), marketingLandingPage);

    }

    /**
     * Print out each project in the collection.
     */
    private void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");
        for (Map.Entry<String, Project> project : projects.entrySet()) {
            System.out.println(project.getValue().getName() + ": " + project.getValue().getTeamMembers().size());
        }

    }

}
