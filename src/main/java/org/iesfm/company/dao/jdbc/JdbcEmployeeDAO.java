package org.iesfm.company.dao.jdbc;

import org.iesfm.company.Employee;
import org.iesfm.company.dao.EmployeeDAO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcEmployeeDAO implements EmployeeDAO {

    private final static String SELECT_EMPLOYEES_BY_DEPARTMENT_DESCRIPTION =
            "SELECT e.* FROM Department d " +
                    "INNER JOIN Employee e ON e.department_name=d.name " +
                    "WHERE d.description=:description";

    private final static String SELECT_DEPARTMENT_EMPLOYEES =
            "SELECT * FROM Employee WHERE department_name=:departmentName";

    private final static String SELECT_EMPLOYEE_BY_NIF =
            "SELECT * FROM Employee WHERE nif=:nif";

    private final static String SELECT_EMPLOYEE_ROLES =
            "SELECT * FROM Role WHERE nif=:nif";

    private final static String INSERT_EMPLOYEE =
            "INSERT INTO Employee(nif, name, surname, department_name) VALUES(:nif, :name, :surname, :departmentName)";

    private final static String INSERT_ROLE =
            "INSERT INTO Role(nif, role) VALUES(:nif, :role)";


    private NamedParameterJdbcTemplate jdbc;

    public JdbcEmployeeDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Employee> listEmployeesByDepartmentDescription(String departmentDescription) {
        Map<String, Object> params = new HashMap<>();
        params.put("description", departmentDescription);
        return jdbc.query(
                SELECT_EMPLOYEES_BY_DEPARTMENT_DESCRIPTION,
                params,
                (rs, rownum) ->
                        new Employee(
                                rs.getString("nif"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                selectEmployeeRoles(rs.getString("nif")),
                                rs.getString("department_name")
                        )
        );
    }

    private List<String> selectEmployeeRoles(String nif) {
        Map<String, Object> params = new HashMap<>();
        params.put("nif", nif);
        return jdbc.query(SELECT_EMPLOYEE_ROLES, params,
                (rs, rownum) ->
                        rs.getString("role")
        );
    }

    @Override
    public List<Employee> listDepartmentEmployees(String departmentName) {
        Map<String, Object> params = new HashMap<>();
        params.put("departmentName", departmentName);
        return jdbc.query(
                SELECT_DEPARTMENT_EMPLOYEES,
                params,
                (rs, rownum) ->
                        new Employee(
                                rs.getString("nif"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                selectEmployeeRoles(rs.getString("nif")),
                                rs.getString("department_name")
                        )
        );
    }

    @Override
    public Employee getEmployee(String nif) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("nif", nif);
            return jdbc.queryForObject(
                    SELECT_EMPLOYEE_BY_NIF,
                    params,
                    (rs, rownum) ->
                            new Employee(
                                    rs.getString("nif"),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    selectEmployeeRoles(rs.getString("nif")),
                                    rs.getString("department_name")
                            )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean insert(Employee employee) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("nif", employee.getNif());
            params.put("name", employee.getName());
            params.put("surname", employee.getSurname());
            params.put("departmentName", employee.getDepartmentName());
            jdbc.update(INSERT_EMPLOYEE, params);
            for(String role: employee.getRoles()) {
                Map<String, Object> roleParams = new HashMap<>();
                roleParams.put("nif", employee.getNif());
                roleParams.put("role", role);
                jdbc.update(INSERT_ROLE, roleParams);
            }
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }
}
