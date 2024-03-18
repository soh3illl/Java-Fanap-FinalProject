package org.example.core.db;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DB {
    private String query = "[QUERY] [TABLE]";

    private Connection connection;
    private Statement statement;

    public DB() {
        try {
            this.connection = DataSource.getConnection();
            this.statement = connection.createStatement();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public String getQuery() {
        return query;
    }

    public DB table(String table) {
        if (table.isEmpty()) {
            throw new IllegalArgumentException("Invalid table name.");
        }

        this.query = query.replace("[TABLE]", table);

        return this;
    }

    public DB insert(List<String> keys, List<String> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Keys doesn't match values");
        }

        query = query.replace("[QUERY]", "INSERT INTO");

        // add keys to the query
        query += "(";
        query += keys.stream().collect(Collectors.joining(", "));
        query += ")";

        // add values
        query += " VALUES(";
        query += values.stream().collect(Collectors.joining(", "));
        query += ")";

        return this;
    }

    public DB select() {
        return this.select("*");
    }

    public DB select(String... fields) {
        String selectQuery = "SELECT [FIELDS] FROM";
        String fields_str = Arrays.asList(fields).stream().collect(Collectors.joining(", "));
        selectQuery = selectQuery.replace("[FIELDS]", fields_str);

        this.query = query.replace("[QUERY]", selectQuery);

        return this;
    }

    public DB update(List<String> keys, List<String> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Keys doesn't match values");
        }

        this.query = query.replace("[QUERY]", "UPDATE");
        this.query += " SET ";

        for (int i = 0; i < keys.size(); i++) {
            query += String.format("%s = %s", keys.get(i), values.get(i));

            if (i != keys.size() - 1) {
                query += ", ";
            }
        }

        return this;
    }

    public DB where(String column, String value) {
        return where(column, "=", value);
    }

    public DB where(String column, String operator, String value) {
        this.query += String.format(" WHERE %s %s %s", column, operator, value);

        return this;
    }

    public DB andWhere(String column, String value) {
        this.andWhere(column, "=", value);
        return this;
    }

    public DB andWhere(String column, String operator, String value) {
        this.query += String.format(" AND %s %s %s", column, operator, value);

        return this;
    }

    public DB orWhere(String column, String value) {
        this.orWhere(column, "=", value);
        return this;
    }

    public DB orWhere(String column, String operator, String value) {
        this.query += String.format(" OR %s %s %s", column, operator, value);

        return this;
    }

    public DB delete() {
        this.query = this.query.replace("[QUERY]", "DELETE FROM");

        return this;
    }

    public ResultSet get() {
        ResultSet result = null;
        try {
            result = this.statement.executeQuery(this.getQuery());
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void execute() {
        try {
            this.statement.executeUpdate(this.getQuery());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
