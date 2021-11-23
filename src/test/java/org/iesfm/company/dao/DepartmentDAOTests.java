package org.iesfm.company.dao;

import org.iesfm.company.CompanyConfiguration;
import org.iesfm.company.Department;
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
@ContextConfiguration(classes = {CompanyConfiguration.class})
public class DepartmentDAOTests {
    private final static Logger log = LoggerFactory.getLogger(Menu.class);

    @Autowired
    private DepartmentDAO departmentDAO;

    @Test
    public void listTest() {
        List<Department> departments = departmentDAO.list();
        for (Department department : departments) {
            log.info(department.toString());
        }
    }

    @Test
    public void insertTest() {
        boolean inserted = departmentDAO.
                insert(
                        new Department(
                                "Informática",
                                "Cosas de ordenadores"
                        )
                );
        Assert.assertFalse(inserted);
    }
}
