package com.PBO.dao;

import com.PBO.entity.Faculty;
import com.PBO.util.DaoService;
import com.PBO.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PBO Tan
 */
public class FacultyDaoImpl implements DaoService<Faculty> {

    @Override
    public List<Faculty> fetchAll() throws SQLException, ClassNotFoundException {
        List<Faculty> faculties = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT id, name FROM faculty";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Faculty faculty = new Faculty();
                        faculty.setId(rs.getInt("id"));
                        faculty.setName(rs.getString("name"));
                        faculties.add(faculty);
                    }

                }
            }
        }
        return faculties;
    }

    @Override
    public int addData(Faculty object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO faculty(name) VALUES(?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getName());
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
