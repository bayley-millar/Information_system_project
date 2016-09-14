/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milba845
 */
public class DatabaseProductManager implements StorageInterface {

    String url = "jdbc:h2:tcp://localhost/project;IFEXISTS=TRUE";

    public DatabaseProductManager() {
    }

    public DatabaseProductManager(String url) {
        this.url = url;
    }

    @Override
    public void saveProduct(Product product) {
        String sql = "merge into products (productID, name, description, category, price, quantityInStock) values (?,?,?,?,?,?);";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            stmt.setString(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setDouble(5, product.getPrice());
            stmt.setInt(6, product.getQuantityInStock());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Product search(String productID) {
        String sql = "select * from products where ProductID = ?;";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, productID);
            ResultSet rs = stmt.executeQuery();

            Product product = new Product();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                Double price = rs.getDouble("price");
                int quantityInStock = rs.getInt("quantityInStock");

                product.setProductID(productID);
                product.setName(name);
                product.setCategory(category);
                product.setDescription(description);
                product.setPrice(price);
                product.setQuantityInStock(quantityInStock);

                return product;
            }

            return null;

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
//               } catch (SQLException ex) {
//            Logger.getLogger(DatabaseProductManager.class.getName()).log(Level.SEVERE, null, ex);
//                   }

    }

    @Override
    public Collection filterByCategory(String category) {
        String sql = "select * from products where category = ?;";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                int quantityInStock = rs.getInt("quantityInStock");
                Product s = new Product(productID, name, description, category, price, quantityInStock);
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Collection returnAllProducts() {
        String sql = "select * from products order by productID;";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                String productID = rs.getString("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                Double price = rs.getDouble("price");
                int quantityInStock = rs.getInt("quantityInStock");
                Product s = new Product(productID, name, description, category, price, quantityInStock);
                products.add(s);
            }
            return products;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Collection returnAllCategories() {
        String sql = "select distinct category from products order by category;";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            List<String> categories = new ArrayList<>();
            while (rs.next()) {
                String category = rs.getString("category");
                String s = new String(category);
                categories.add(s);
            }
            return categories;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        String sql = "delete from products where productID = ?";
        try (
                Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, product.getProductID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

}
