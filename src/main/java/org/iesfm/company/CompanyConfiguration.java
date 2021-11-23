package org.iesfm.company;

import org.iesfm.company.dao.DepartmentDAO;
import org.iesfm.company.dao.EmployeeDAO;
import org.iesfm.company.dao.jdbc.JdbcDepartmentDAO;
import org.iesfm.company.dao.jdbc.JdbcEmployeeDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
@PropertySource("application.properties")
public class CompanyConfiguration {


    @Bean
    public Menu menu(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO, Scanner scanner) {
        return new Menu(scanner, employeeDAO, departmentDAO);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbc(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public EmployeeDAO employeeDAO(NamedParameterJdbcTemplate jdbc) {
        return new JdbcEmployeeDAO(jdbc);
    }

    @Bean
    public DepartmentDAO departmentDAO(NamedParameterJdbcTemplate jdbc) {
        return new JdbcDepartmentDAO(jdbc);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public DataSource dataSource(
            @Value("${database.url}") String url,
            @Value("${database.user}") String username,
            @Value("${database.password}") String password
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
