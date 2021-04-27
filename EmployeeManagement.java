import java.util.Scanner;

import com.ideas2it.employeemanagement.view.EmployeeView;
import com.ideas2it.projectmanagement.view.ProjectView;

/**
 * We perform create, read, update, delete operations using JDBC
 *
 * @version 1.0 05-04-2021
 * @author Kirubakarane R
 */
public class EmployeeManagement {
 
    /**
     * We choose an option of which side we need to make our entries.
     * Here are two methods employee view and project view.
     * In employee view we perform CRUD operations for an employee.
     * In project view we perform CRUD operations for a project.
     */
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String printStatement = "\n\tCHOOSE AN OPTION"
                + "\n1-EMPLOYEE | 2-PROJECT | 3-EXIT\n"
                + "\nENTER AN OPTION: ";
        int userOption;
        
        do {
            System.out.print(printStatement);
            userOption = scanner.nextInt();

            switch (userOption) {
                case 1:  EmployeeView employeeView = new EmployeeView();
                         employeeView.selectCrudOptions();
                         break;
                case 2:  ProjectView projectView = new ProjectView();
                         projectView.selectCrudOptions();
                         break;
                case 3:  System.out.println("\nTHANKS FOR YOUR VISIT");
                         break;
                default: System.out.println("\nWARNING: INVALID INPUT");
                         break;
            }
        } while (3 != userOption);
    }
}