package org.iesfm.company;

import java.util.List;
import java.util.Objects;

public class Employee {

    private String nif;
    private String name;
    private String surname;
    private List<String> roles;
    private String departmentName;

    public Employee(String nif, String name, String surname, List<String> roles, String departmentName) {
        this.nif = nif;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
        this.departmentName = departmentName;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "nif='" + nif + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roles=" + roles +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(nif, employee.nif) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(roles, employee.roles) && Objects.equals(departmentName, employee.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, name, surname, roles, departmentName);
    }
}
