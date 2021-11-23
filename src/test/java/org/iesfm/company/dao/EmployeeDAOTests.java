package org.iesfm.company.dao;

import org.iesfm.company.CompanyConfiguration;
import org.iesfm.company.Employee;
import org.iesfm.company.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CompanyConfiguration.class })
public class EmployeeDAOTests {
    private final static Logger log = LoggerFactory.getLogger(Menu.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    public void listTest() {
        List<Employee> employeeList = employeeDAO.listDepartmentEmployees("Marketing");
        for(Employee employee:employeeList) {
            log.info(employee.toString());
        }
    }

    @Test
    public void listByDescriptionTest() {
        List<Employee> employeeList = employeeDAO.listEmployeesByDepartmentDescription("Labores de publicidad y gestión de clientes");
        for(Employee employee:employeeList) {
            log.info(employee.toString());
        }
    }
    @Test
    public void getPepa() {
        Employee peppa = employeeDAO.getEmployee("00000000X");
        log.info(
                peppa.toString()
        );
    }

    @Test
    public void getNull() {
        Employee otro = employeeDAO.getEmployee("00000333X");
        Assert.assertNull(otro);
    }

}
