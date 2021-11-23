package org.iesfm.company.dao;

import org.iesfm.company.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> list();

    boolean insert(Department department);
}
