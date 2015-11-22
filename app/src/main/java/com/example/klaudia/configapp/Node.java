package com.example.klaudia.configapp;

/**
 * Created by Klaudia on 2015-11-15.
 */
public class Node {
    private int id;
    private String name = "";
    private String set = "";
    private Category categoryObj;
    private String category = "";
    private String image = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategoryObj() {
        return categoryObj;
    }

    public void setCategoryObj(Category categoryObj) {
        this.categoryObj = categoryObj;
    }
}
