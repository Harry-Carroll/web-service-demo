package com.kainos.ea.Dao;

import com.kainos.ea.Models.Employee;


import java.sql.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class EmployeeDao {




    public Employee selectEmployeeByID(Connection c, int id) throws SQLException{
        Employee dbEmp = new Employee();
        Statement st = c.createStatement();
        String selectLastEmployee = "SELECT * FROM Delivery WHERE DeliveryEmpID = ?;";
        PreparedStatement preparedStmt = c.prepareStatement(selectLastEmployee);
        preparedStmt.setInt(1, id);
        ResultSet rs = preparedStmt.executeQuery();
        while (rs.next()) {
            dbEmp = new Employee(rs.getInt("deliveryEmpId"),
                     rs.getInt("salary"),rs.getString("dName"), rs.getString("bankNo"),
                    rs.getString("NIN"));
        }
        return dbEmp;
    }

//    public Employee selectEmpByName(Connection c, String name) throws SQLException{
//        Employee dbEmp = new Employee();
//        Statement st = c.createStatement();
//        String selectLastEmployee = "SELECT * FROM Delivery WHERE dName = '?'';";
//        PreparedStatement preparedStmt = c.prepareStatement(selectLastEmployee);
////        preparedStmt.setString(1, name);
//        ResultSet rs = preparedStmt.executeQuery();
//        while (rs.next()) {
//            dbEmp = new Employee(rs.getInt("deliveryEmpId"),
//                    rs.getInt("salary"),rs.getString("dName"), rs.getString("bankNo"),
//                    rs.getString("NIN"));
//        }
//        return dbEmp;
//    }
    public List<Employee> getEmp(Connection c) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT deliveryEmpId , dName , salary , bankNo , NIN\n" +
                        "FROM Delivery; ");

        while (rs.next()) {
            Employee emp = new Employee(
                    rs.getInt("deliveryEmpId"),
                    rs.getInt("salary"),
                    rs.getString("dName"),
                    rs.getString("bankNo"),
                    rs.getString("NIN")
            );
            employees.add(emp);
        }
        return employees;
    }



    public void insertToEmployee(Employee e, Connection c) throws SQLException {


        String query = "insert into Delivery(deliveryEmpId, dName, salary, bankNo, NIN) values (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = c.prepareStatement(query);
        preparedStatement.setInt(1, e.getDeliveryEmpId());
        preparedStatement.setString(2, e.getName());
        preparedStatement.setInt(3, e.getSalary());
        preparedStatement.setString(4, e.getBankNo());
        preparedStatement.setString(5, e.getNIN());
        preparedStatement.execute();
    }
}
