package com.miedviediev.cs.Database;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(long id) throws SQLException;

    Optional<Object> get(long id, String field) throws SQLException;

    List<Object> getAll(String field) throws SQLException;

    List<T> getAll() throws SQLException;

    boolean save(T t) throws SQLException;

    boolean update(long id, String query) throws SQLException;

    boolean delete(long id) throws SQLException;
}
