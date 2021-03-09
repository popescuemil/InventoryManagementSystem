package com.barclays.ims.Models;

public class Item {
    private int ItemID;
    private String ItemName;
    private double ItemCost;

    public Item(int itemID, String itemName, double itemCost) {
        setItemID(itemID);
        setItemName(itemName);
        setItemCost(itemCost);
    }

    public Item(String itemName, double itemCost) {
        ItemName = itemName;
        ItemCost = itemCost;
    }

    public double getItemCost() {
        return ItemCost;
    }

    public String getItemName() {
        return ItemName;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemName(String itemName) {
        this.ItemName = itemName;
    }

    public void setItemID(int itemID) {
        this.ItemID = itemID;
    }
    
    public void setItemCost(double itemCost) {
        this.ItemCost = itemCost;
    }

    @Override
    public String toString() {
        return "ItemID=" + getItemID() + ", ItemName=" + getItemName() + ", ItemCost=" + getItemCost();
    }
}
