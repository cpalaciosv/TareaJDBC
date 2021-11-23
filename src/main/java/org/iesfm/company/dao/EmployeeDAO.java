package org.iesfm.company.dao;

import org.iesfm.company.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> listEmployeesByDepartmentDescription(String departmentDescription);

    List<Employee> listDepartmentEmployees(String departmentName);

    Employee getEmployee(String nif);

    boolean insert(Employee employee);
}
