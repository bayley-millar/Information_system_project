/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author milba845
 */
public interface StorageInterface {
    public void saveProduct(Product product);
    
    public Product search(String ID);
        
    public Collection filterByCategory(String category);
        
    public Collection returnAllProducts();
        
    public Collection returnAllCategories();
        
    public void deleteProduct(Product product);
        
}
