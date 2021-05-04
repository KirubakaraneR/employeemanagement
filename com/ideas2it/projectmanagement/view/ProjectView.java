package com.ideas2it.projectmanagement.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.projectmanagement.controller.ProjectController;

/**
 * We get the input values and perform display operation.
 *
 * @version 1.0 04-05-2021
 * @author Kirubakarane R
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
                + "\n5.Unassign Project\n6.Restore Project\n7.Delete\n8.Exit";
        int userOption;
        
        do {
            System.out.println(printStatement);
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  createProject();
                         break;
                case 2:  chooseOptionForDisplay();
                         break;
                case 3:  updateProject();
                         break;
                case 4:  assignEmployeeToProject();
                         break;
                case 5:  unassignEmployeeFromProject();
                         break;
                case 6:  restoreProject();
                         break;
                case 7:  deleteProject();
                         break;
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
            LocalDate startDate = getProjectStartDate();
            LocalDate endDate = getProjectEndDate();
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
    public LocalDate getProjectStartDate() {
        System.out.println("\n[NOTE: Use this format YYYY-MM-DD]");
        System.out.print("\nPROJECT START DATE: ");
        return LocalDate.parse(scanner.next());
    }

    /**
     * We get the end date of the project
     *
     * @return project end date
     */
    public LocalDate getProjectEndDate() {
        System.out.println("\n[NOTE: Use this format YYYY-MM-DD]");
        System.out.print("\nPROJECT END DATE: ");
        return LocalDate.parse(scanner.next());
    }

    /**
     * We choose an option here to perform the display operation
     */
    public void chooseOptionForDisplay() {
        System.out.println("\nChoose an option to display"
                + "\n1.Individual Project\n2.All Project"
                + "\n3.Display Assigned Employees\n4.Exit");
        int userOption = scanner.nextInt();

        switch (userOption) {
            case 1:  displayIndividualProject();
                     break;
            case 2:  displayAllProject();
                     break;
            case 3:  displayAssignedEmployee();
                     break;
            case 4:  break;
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

    /**
     * We choose options for update and update various columns
     */
    public void updateProject() {
        System.out.print("\nEnter the project id you need to update: ");
        int id = scanner.nextInt();
   
        if (projectController.checkIdExistOrNot(id)) {
            String printStatement = "Choose an option"
                    + "\n1.Project name\n2.Start date\n3.End date"
                    + "\n4.Project status\n5.Exit";
            int userOption;
        
            do {
                System.out.println(printStatement);
                userOption = scanner.nextInt();

                switch (userOption) {
                    case 1:  System.out.print("\nPROJECT NAME TO UPDATE: ");
                             String name = scanner.skip("[\r\n]+").nextLine();
 
                             if (projectController.updateProjectName(id, name)) {
                                 System.out.println("Datas are updated successfully.");
                             } else {
                                 System.out.println("There is an issue in updating datas.");
                             }
                             break;
                    case 2:  System.out.print("\nPROJECT START DATE TO UPDATE: ");
                             LocalDate startDate = LocalDate.parse(scanner.next());

                             if (projectController.updateStartDate(id, startDate)) {
                                 System.out.println("Datas are updated successfully.");
                             } else {
                                 System.out.println("There is an issue in updating datas.");
                             }
                             break;
                    case 3:  System.out.print("\nPROJECT END DATE TO UPDATE: ");
                             LocalDate endDate = LocalDate.parse(scanner.next());

                             if (projectController.updateEndDate(id, endDate)) { 
                                 System.out.println("Datas are updated successfully.");
                             } else {
                                 System.out.println("There is an issue in updating datas.");
                             }
                             break;
                    case 4:  System.out.print("\nPROJECT STATUS TO UPDATE: ");
                             String status = scanner.next();
   
                             if (projectController.updateProjectStatus(id, status)) {
                                 System.out.println("Datas are updated successfully.");
                             } else {
                                 System.out.println("There is an issue in updating datas.");
                             }
                    case 5:  break;
                    default: System.out.println("WARNING: Invalid Input");
                             continue;
                }   
            } while (5 != userOption);
        } else {
            System.out.println("\nThe given id is not available.");
        }
    }

    /**
     * We delete entire details of a project
     */
    public void deleteProject() {
        int userOption;

        do {
            System.out.print("\nEnter the project id you need to delete: ");
            int id = scanner.nextInt();

            if (projectController.checkIdExistOrNot(id)) {

                if (projectController.deleteProject(id)) {
                    System.out.println("Datas are deleted successfully.");
                } else {
                    System.out.println("There is an issue in deleting datas.");
                }
            } else {
                System.out.println("\nThe given id is not available.");
            }
            System.out.println("\nPress 1 to continue delete"
                    + "\n      0 to exit");
            userOption = scanner.nextInt();
        } while (1 == userOption);
    }

    /**
     * We assign list of employees to a project
     */
    public void assignEmployeeToProject() {
        int userOption;
  
        do {
            System.out.print("\nEnter the project id: ");
            int id = scanner.nextInt(); 

            if (projectController.checkIdExistOrNot(id)) {
                List<List<String>> employeeIdList = getEmployeeIdList(id);

                if (projectController.checkIsEmpty(employeeIdList.get(0))) {
                    System.out.println("Employees are already assigned to project");
                } else {
                    projectController.assignEmployeeToProject(id, employeeIdList.get(0));
                }
            } else {
                System.out.println("\nThe given id is not available.");
            }
            System.out.println("\nPress 1 to continue delete"
                    + "\n      0 to exit");
            userOption = scanner.nextInt();
        } while (1 == userOption);
    }

    /**
     * We unassign list of employees from the project
     */
    public void unassignEmployeeFromProject() {
        int userOption;
  
        do {
            System.out.print("\nEnter the project id: ");
            int id = scanner.nextInt();
 
            if (projectController.checkIdExistOrNot(id)) {
                List<List<String>> employeeIdList = getEmployeeIdList(id);

                if (projectController.checkIsEmpty(employeeIdList.get(1))) {
                    System.out.println("\nThere is no employee or project available to unassign.");
                } else {
                    projectController.unassignEmployeeFromProject(id, employeeIdList.get(1));
                }
            } else {
                System.out.println("\nThe given id is not available.");
            }
            System.out.println("\nPress 1 to continue delete"
                    + "\n      0 to exit");
            userOption = scanner.nextInt();
        } while (1 == userOption);
    }

    /**
     * We get the employee id list.
     *
     * @param id - project id
     *
     * @return List of employee id
     */
    public List<List<String>> getEmployeeIdList(int id) {
        System.out.print("\nEnter the number of employees: ");
        int employeeCount = scanner.nextInt();
        int index = 0;
        List<String> employeeIdList = new ArrayList<String>();

        while (index < employeeCount) {
            System.out.print("\nEnter the employee id: ");
            String employeeId = scanner.next();
            employeeIdList.add(employeeId);
            index++;
        }
        return projectController.getEmployeeIdList(id, employeeIdList);
    }

    /**
     * We display list of employees who are assigned to project.
     */
    public void displayAssignedEmployee() {
        System.out.print("Enter the project id:"); 
        int id = scanner.nextInt();
   
        if (projectController.checkIdExistOrNot(id)) {
            List<String> employeeValues = projectController.getAssignedEmployees(id);
   
            for (String employee : employeeValues) {
                System.out.println(employee);
            }
        } else {
            System.out.println("\nThe given id is not available.");
        }
    }

    /**
     * We restore the deleted project from the table.
     */
    public void restoreProject() {  
        System.out.print("\nEnter the Project Id: ");
        int id = scanner.nextInt();
        
        if (projectController.checkIdIsDeleted(id)) {

            if (projectController.restoreProject(id)) {
                System.out.print("\nDatas are restored successfully");
            } else {
                System.out.print("\nThere is an error in restoring data.");
            }
        } else {
            System.out.println("\nThe given project id is not available.");
        }
    }
}