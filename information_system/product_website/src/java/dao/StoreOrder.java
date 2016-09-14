/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class StoreOrder {

    String url = "jdbc:h2:tcp://localhost/project;IFEXISTS=TRUE";

    public StoreOrder() {
    }

    public StoreOrder(String url) {
        this.url = url;
    }

    public void save(Order order) {

        String sql1 = "insert into orders (orderID,date,customer)values(?,?,?);";
        String sql2 = "merge into orders (orderID,date,customer)values(?,?,?);";
        String sql3 = "merge into orders (orderID,date,customer)values(?,?,?);";
        Connection dbCon = JdbcConnection.getConnection(url);
        try {
            try (PreparedStatement stmt1 = dbCon.prepareStatement(sql1,
                    Statement.RETURN_GENERATED_KEYS)) {
                stmt1.setDate(1, (Date) order.getDate());
                stmt1.setObject(2, order.getCustomer());
                stmt1.executeUpdate();
                ResultSet rs = stmt1.getGeneratedKeys();
                if (rs.next()) {
                    Integer generatedId = rs.getInt(1);

                } else {
                    throw new RuntimeException("Problem getting generated ID");
                }
            
            
                // commit the transaction
                dbCon.setAutoCommit(false);
                stmt1.executeUpdate(sql1);
                dbCon.commit();

                Timestamp timestamp = new Timestamp(order.getDate().getTime());
            }
        } catch (SQLException sqlEx1) {
            try {
                dbCon.rollback();
                // turn auto-commit back on (in case the connection pool doesn't do it)
                dbCon.setAutoCommit(true);

            } catch (SQLException sqlEx2) {

                throw new DAOException(sqlEx2.getMessage(), sqlEx2);
            }
            throw new DAOException(sqlEx1.getMessage(), sqlEx1);
        } finally {
            try {
                dbCon.close();
            } catch (SQLException sqlEx3) {
                throw new DAOException(sqlEx3.getMessage(), sqlEx3);
            }
        }
    }

}
