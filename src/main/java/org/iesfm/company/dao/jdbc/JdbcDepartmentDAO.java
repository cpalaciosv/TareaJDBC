package org.iesfm.company.dao.jdbc;

import org.iesfm.company.Department;
import org.iesfm.company.Employee;
import org.iesfm.company.dao.DepartmentDAO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcDepartmentDAO implements DepartmentDAO {
    private final static String SELECT_DEPARTMENTS =
            "SELECT * FROM Department";

    private final static String INSERT_DEPARTMENT =
            "INSERT INTO Department(name, description) VALUES(:name, :description)";
    
    private NamedParameterJdbcTemplate jdbc;

    public JdbcDepartmentDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Department> list() {
        return jdbc.query(
                SELECT_DEPARTMENTS,
                (rs, rownum) ->
                        new Department(
                                rs.getString("name"),
                                rs.getString("description")
                        )
        );
    }

    @Override
    public boolean insert(Department department) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", department.getName());
            params.put("description", department.getDescription());
            jdbc.update(INSERT_DEPARTMENT, params);
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }
}
