package com.appkotlin.isa2android2310;

import java.util.ArrayList;
import java.util.List;

public class SingletonLastList {

    private static SingletonLastList instance;
    private List<FoodItem> foodItemList;

    double lasttotal;

    private SingletonLastList() {
        foodItemList = new ArrayList<>();
    }

    public static synchronized SingletonLastList getInstance() {
        if (instance == null) {
            instance = new SingletonLastList();
        }
        return instance;
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public void setFoodItemList(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public void setTotal(double t){
        this.lasttotal=t;
    }
    public double getTotal(){
        return lasttotal;
    }


}
