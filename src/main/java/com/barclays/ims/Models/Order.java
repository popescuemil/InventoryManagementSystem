package com.barclays.ims.Models;

import java.util.List;

public class Order {
    private int OrderID;
    private int CustomerID;
    private List<Integer> ItemIDs;

    public Order(int orderID, int customerID, List<Integer> itemIDs) {
        OrderID = orderID;
        CustomerID = customerID;
        ItemIDs = itemIDs;
    }
    
    public Order(int customerID, List<Integer> itemIDs) {
        CustomerID = customerID;
        ItemIDs = itemIDs;
    }
    
    public int getOrderID() {
        return OrderID;
    }

    public List<Integer> getItemIDs() {
        return ItemIDs;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setItemIDs(List<Integer> itemIDs) {
        this.ItemIDs = itemIDs;
    }

    public void setCustomerID(int customerID) {
        this.CustomerID = customerID;
    }

    public void setOrderID(int orderID) {
        this.OrderID = orderID;
    }

    @Override
    public String toString() {
        return "OrderID=" + getOrderID() + ", CustomerID=" + getCustomerID() + ", ItemIDs=" + getItemIDs();
    }
}
