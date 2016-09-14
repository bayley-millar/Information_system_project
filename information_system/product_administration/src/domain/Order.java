/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author milba845
 */
public class Order {
    
    private Integer orderID;
    private Date date;
    private Customer customer;
    private ArrayList<OrderItem> items;


    public Order(Customer customer) {
        this.customer=customer;
        this.date = new Date();
        items = new ArrayList();
    }
    public void addItem(OrderItem item) {
        items.add(item);
    }
    public double getTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getItemTotal();
        }
        return total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
    
    
}
