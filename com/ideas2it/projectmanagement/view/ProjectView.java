package com.ideas2it.projectmanagement.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.projectmanagement.controller.ProjectController;

/**
 * We get the input values and perform display operation
 */
public class ProjectView {
    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController
            = new ProjectController();
     
    /**
     * We get input from user and select an option which we need to 
     * perform. Options such as create, read, update and delete
     */
    public void selectCrudOptions() {
        String printStatement = "\nChoose the option you need to perform"
                + "\n1.Create\n2.Display\n3.Update\n4.Assign Project"
                + "\n5.Unassign Project\n6.Display Assigned Employees\n7.Delete\n8.Exit";
        int userOption;
        
        do {
            System.out.println(printStatement);
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  createProject();
                         break;
                case 2:  chooseOptionForDisplay();
                         break;
                /*case 3:  updateProject();
                         break;
                case 4:  assignEmployeeToProject();
                         break;
                case 5:  unassignEmployeeFromProject();
                         break;
                case 6:  displayAssignedEmployee();
                         break;
                case 7:  deleteProject();
                         break;*/
                case 8:  System.out.println("\nYou are back to home page.");
                         break;
                default: System.out.println("\nWARNING: Invalid Entry");
                         continue;
            }
        } while (8 != userOption);  
    }

    /**
     * We create project details.
     * Details such as name, start date, end date
     */
    public void createProject() {
        System.out.print("\nEnter the number of projects you need to create: ");
        int projectCount = scanner.nextInt();
        int index = 0;

        while (index < projectCount) {
            String name = getProjectName();
            Date startDate = getProjectStartDate();
            Date endDate = getProjectEndDate();
            System.out.println("Whether the project is started or not"
                + "\nType \"yes\" if started or else \"no\"");
            String inputValue = scanner.next().toLowerCase();
            String status = (inputValue.equals("yes")) ? "yes" : "no";

            if (projectController.createProject(name, startDate, 
                    endDate, status)) {
                System.out.println("Datas are added successfully in table.");
            } else {
                System.out.println("There is an issue in adding datas.");
            }
            index++;
        }
    }

    /**
     * We get the name of the project
     *
     * @return project name
     */
    public String getProjectName() {
        System.out.print("\nPROJECT NAME: ");
        return scanner.skip("[\r\n]+").nextLine();
    }

    /**
     * We get the start date of the project
     *
     * @return project start date
     */
    public Date getProjectStartDate() {
        System.out.println("\n[NOTE: Use this format YYYY-MM-DD]");
        System.out.print("\nPROJECT START DATE: ");
        return Date.valueOf(scanner.next());
    }

    /**
     * We get the end date of the project
     *
     * @return project end date
     */
    public Date getProjectEndDate() {
        System.out.println("\n[NOTE: Use this format YYYY-MM-DD]");
        System.out.print("\nPROJECT END DATE: ");
        return Date.valueOf(scanner.next());
    }

/**
     * We choose an option here to perform the display operation
     */
    public void chooseOptionForDisplay() {
        System.out.println("\nChoose an option to display"
                + "\n1.Individual Project\n2.All Project\n3.Exit");
        int userOption = scanner.nextInt();

        switch (userOption) {
            case 1:  displayIndividualProject();
                     break;
            case 2:  displayAllProject();
                     break;
            case 3:  break;
            default: System.out.println("\nWARNING: Invalid Entry");
                     break;
        }
    }

    /**
     * We display details of an individual project
     */
    public void displayIndividualProject() {
         System.out.print("\nEnter the project id you need to display: ");
         int id = scanner.nextInt();
 
         if (projectController.checkIdExistOrNot(id)) {
             List<String> projectValue 
                     = projectController.getIndividualProjectForDisplay(id);
         
             for (String values : projectValue) {
                 System.out.println(values);
             }
         } else {
             System.out.println("\nThe given id is not available.");
         }
    }

    /**
     * We display details of all the projects
     */
    public void displayAllProject() {
        List<String> projectValues = projectController.getProjectForDisplay();
 
        for (String values : projectValues) {
            System.out.println(values);
        }
    }

}