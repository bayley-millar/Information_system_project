    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author milba845
 */
public class StoreProducts implements StorageInterface {

    private static final Map<String, Set<Product>> productsInCategory = new HashMap<>();
    private static final Map<String, Product> productIDList = new HashMap();

    @Override
    public void saveProduct(Product product) {
        productIDList.put(product.getProductID(), product);
        if (productsInCategory.containsKey(product.getCategory())) {
            Set products = productsInCategory.get(product.getCategory());
            products.add(product);
        } else {
            Set products = new TreeSet();
            products.add(product);
            productsInCategory.put(product.getCategory(), products);
        }
    }

    @Override
    public Product search(String ID) {
        return productIDList.get(ID);
    }

    @Override
    public Collection filterByCategory(String category) {
        if (productsInCategory.containsKey(category)) {
            return productsInCategory.get(category);
        }
        return new TreeSet();
    }

    @Override
    public Collection returnAllProducts() {
        return productIDList.values();
    }

    @Override
    public Collection returnAllCategories() {
        return productsInCategory.keySet();
    }

    @Override
    public void deleteProduct(Product product) {
        productIDList.remove(product.getProductID());
        if (productsInCategory.containsKey(product.getCategory())) {
            Set products = productsInCategory.get(product.getCategory());
            products.remove(product);
        }
    }

}
