package com.example.quizzapp;

public class Item {
    private String itemName;
    private int imageResId;

    public Item(String itemName, int imageResId) {
        this.itemName = itemName;
        this.imageResId = imageResId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImageResId() {
        return imageResId;
    }
}
