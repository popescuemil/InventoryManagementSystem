package com.barclays.ims.Models;

public class Order {
    private int OrderID;
    private int CustomerID;
    private int[] ItemIDs;

    public Order(int orderID, int customerID, int[] itemIDs) {
        OrderID = orderID;
        CustomerID = customerID;
        ItemIDs = itemIDs;
    }
    
    public int getOrderID() {
        return OrderID;
    }

    public int[] getItemIDs() {
        return ItemIDs;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setItemIDs(int[] itemIDs) {
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
        return "OrderID=" + getOrderID() + ", CustomerID='" + getCustomerID() + ", ItemIDs='" + getItemIDs();
    }
}
