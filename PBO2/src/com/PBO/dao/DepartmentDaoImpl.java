package com.PBO.dao;

import com.PBO.entity.Department;
import com.PBO.entity.Faculty;
import com.PBO.util.DaoService;
import com.PBO.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DaoService<Department> {

    @Override
    public List<Department> fetchAll() throws SQLException, ClassNotFoundException {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT d.id, d.name, d.faculty_id, f.name AS faculty_name FROM department d JOIN faculty f ON d.faculty_id = f.id";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Faculty faculty = new Faculty();
                        faculty.setId(rs.getInt("faculty_id"));
                        faculty.setName(rs.getString("faculty_name"));

                        Department department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        department.setFaculty(faculty);

                        departments.add(department);
                    }
                }
            }
        }
        return departments;
    }

    @Override
    public int addData(Department object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO department(name, faculty_id) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getName());
                ps.setInt(2, object.getFaculty().getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
}
