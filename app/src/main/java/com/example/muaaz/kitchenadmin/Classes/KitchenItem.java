package com.example.muaaz.kitchenadmin.Classes;

/**
 * Created by muaaz on 2017/09/09.
 */

public class KitchenItem {
    public String itemName;
    public double itemPrice;
    public String description;
    public String cloudStorageUri;

    public KitchenItem(){}

    public KitchenItem(String itemName, double itemPrice, String description, String cloudStorageUri){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.description = description;
        this.cloudStorageUri = cloudStorageUri;
    }
}
