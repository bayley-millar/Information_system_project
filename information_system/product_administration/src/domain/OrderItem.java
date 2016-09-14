/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author milba845
 */
public class OrderItem {
    
    private Integer quantityPurchased;
    private Product product;
    private Order order;

    public OrderItem(Integer quantityPurchased, Product product, Order order) {
        this.quantityPurchased = quantityPurchased;
        this.product = product;
        this.order = order;
    }

    public OrderItem() {
    }
    

    public Integer getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Integer quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public Double getItemTotal(){
        Double result = quantityPurchased * product.getPrice();
        return result;
    }


    
    

}
