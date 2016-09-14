/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;
import net.sf.oval.constraint.*;

/**
 *
 * @author milba845
 */
public class Product implements Comparable<Product> {
    @NotNull(message = "ID must be provided.")
    @NotBlank(message = "ID must be provided.")
    private String productID;
    
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min = 2, message = "Name must contain at least two characters.")
    private String name;
    
    private String description;
    
    @NotNull(message = "category must be provided.")
    @NotBlank(message = "category must be provided.")
    @Length(min = 2, message = "category must contain at least two characters.")
    private String category;
    
    @NotNegative(message = "Price must be a positive number.")
    @NotNull(message = "Price must be provided.")
    private Double price;
    
    @NotNegative(message = "Quantity must be a positive number.")
    @NotNull(message = "Quantity must be provided.")
    private int quantityInStock;

    public Product(String productID, String name, String description, String category, Double price, int quantityInStock) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Product() {
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "productID:" + productID + ", name:" + name + ", description:" + description + ", category:" + category + ", price:" + price + ", quantityInStock:" + quantityInStock;
    }

    @Override
    public int compareTo(Product anotherID) {
        String myID = this.getProductID();
        String theirID = anotherID.getProductID();
        return myID.compareTo(theirID);

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.productID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        return true;
    }

}
