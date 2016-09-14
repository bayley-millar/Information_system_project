/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.DAOException;
import dao.JdbcConnection;
import dao.StorageInterface;
import domain.Customer;
import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author milba845
 */
public class StoreCustomer{
    String url = "jdbc:h2:tcp://localhost/project;IFEXISTS=TRUE";

    public StoreCustomer() {
    }
    public StoreCustomer(String url) {
        this.url = url;
    }
    
   
    public void saveCustomer(Customer customer) {
        String sql = "merge into customers (userName,name,email,address,creditCardNumber,password)values(?,?,?,?,?,?);";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            stmt.setString(1,customer.getUserName() );
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getCreditCardNumber());
            stmt.setString(6, customer.getPassword());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    public Customer login(String userName, String password){
        String sql = "select * from customers where userName = ? and password = ?;";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            Customer customer = new Customer();
            if (rs.next()) {
                
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String creditCardNumber = rs.getString("creditCardNumber");
                

                customer.setUserName(userName);
                customer.setName(name);
                customer.setEmail(email);
                customer.setAddress(address);
                customer.setCreditCardNumber(creditCardNumber);
                customer.setPassword(password);

                return customer;

            }
            return null;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    public void deleteCustomer(Customer customer) {
        String sql = "delete from customers where userName = ?";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, customer.getUserName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
        
    
}
