package com.barclays.ims.Models;

public class Customer {
    private int CustomerID;
    private String CustomerName;

    public Customer(int customerID, String customerName) {
        CustomerID = customerID;
        CustomerName = customerName;
    }

    public Customer(String customerName) {
        CustomerName = customerName;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    public void setCustomerID(int customerID) {
        this.CustomerID = customerID;
    }

    @Override
    public String toString() {
        return "CustomerID=" + getCustomerID() + ", CustomerName=" + getCustomerName();
    }
}
