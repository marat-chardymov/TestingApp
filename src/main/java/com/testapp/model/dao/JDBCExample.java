package com.testapp.model.dao;

import com.testapp.model.entities.Subject;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {

    public static void main(String[] argv) throws IOException, SQLException {

        MyDataSource ds = MyDataSource.getInstance();
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM subjects");
        List<Subject> subjectList = new ArrayList<Subject>();
        while (rs.next()) {
            subjectList.add(new Subject(rs.getString("name")));
        }
        print(subjectList);
    }

    public static void print(List<Subject> subjectList) {
        for (Subject subject : subjectList) {
            System.out.println(subject.getName());
        }
    }
}