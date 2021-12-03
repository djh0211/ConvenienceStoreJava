package com.majorProject;

public class Item {
    private final String INDEX;
    private String itemName;
    private int itemPrice;
    private int inventoryCount;
    private boolean isRemoved;

    public Item(String index, String itemName, int itemPrice, int inventoryCount, boolean isRemoved) {
        this.INDEX = index;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.inventoryCount = inventoryCount;
        this.isRemoved = isRemoved;
    }

    public String getINDEX() {
        return INDEX;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    @Override
    public String toString() {
        return itemName+","+INDEX+","+itemPrice+","+inventoryCount;
    }
}
