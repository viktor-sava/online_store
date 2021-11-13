package com.example.online_store.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Category implements Serializable {

    private int id;

    private String name;

    private Timestamp modifiedDate;

    private String picture;

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

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }


    public static final class CategoryBuilder {
        private final Category category;

        private CategoryBuilder() {
            category = new Category();
        }

        public CategoryBuilder id(int id) {
            category.setId(id);
            return this;
        }

        public CategoryBuilder name(String name) {
            category.setName(name);
            return this;
        }

        public CategoryBuilder modifiedDate(Timestamp modifiedDate) {
            category.setModifiedDate(modifiedDate);
            return this;
        }

        public CategoryBuilder picture(String picture) {
            category.setPicture(picture);
            return this;
        }

        public Category build() {
            return category;
        }
    }
}