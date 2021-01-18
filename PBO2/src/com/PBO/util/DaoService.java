package com.PBO.util;

import java.sql.SQLException;
import java.util.List;

/**
 * @author PBO Tan
 */
public interface DaoService<T> {

    List<T> fetchAll() throws SQLException, ClassNotFoundException;

    int addData(T object) throws SQLException, ClassNotFoundException;
}
