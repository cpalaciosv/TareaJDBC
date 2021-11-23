package org.iesfm.company;

import org.iesfm.company.dao.DepartmentDAO;
import org.iesfm.company.dao.EmployeeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final static Logger log = LoggerFactory.getLogger(Menu.class);

    private Scanner scanner;
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    public Menu(Scanner scanner, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        this.scanner = scanner;
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
    }

    public void run() {
        String option = askOption();

        while (!option.equals("4")) {
            if (option.equals("1")) {
                List<Department> departements = departmentDAO.list();
                for (Department department : departements) {
                    log.info(department.getName());
                }
            } else if (option.equals("2")) {
                log.info("Introduce el nombre del departamento");
                String departmentName = scanner.nextLine();
                List<Employee> employees = employeeDAO.listDepartmentEmployees(departmentName);
                for (Employee employee : employees) {
                    log.info(employee.getName() + " " + employee.getSurname() + ". " + employee.getRole());
                }
            } else if(option.equals("3")) {
                Department department = askDepartment();
                departmentDAO.insert(department);
            }

            option = askOption();
        }
    }

    private Department askDepartment() {
        log.info("Introduce los datos del departamento");
        log.info("Nombre:");
        String name = scanner.nextLine();
        log.info("Descripción:");
        String description = scanner.nextLine();
        return new Department(name, description);
    }

    private String askOption() {
        printMenu();
        String option = scanner.nextLine();
        while (!option.equals("1") &&
                !option.equals("2") &&
                !option.equals("3") &&
                !option.equals("4")
        ) {
            log.info("Opción inválida, debe elegir una de las opciones disponibles");
            printMenu();
            option = scanner.nextLine();
        }
        return option;
    }


    private void printMenu() {
        log.info("Menú de opciones:");
        log.info("1. Listar departamentos");
        log.info("2. Mostrar empleados de un departamento");
        log.info("3. Insertar un departamento");
        log.info("4. Salir");
    }
}
