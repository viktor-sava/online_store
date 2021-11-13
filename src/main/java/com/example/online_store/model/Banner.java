package com.example.online_store.model;

import java.io.Serializable;

public class Banner implements Serializable {

    private int id;

    private String picture;

    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                '}';
    }

    public static BannerBuilder builder() {
        return new BannerBuilder();
    }

    public static final class BannerBuilder {

        private final Banner banner;

        private BannerBuilder() {
            banner = new Banner();
        }

        public BannerBuilder id(int id) {
            banner.setId(id);
            return this;
        }

        public BannerBuilder picture(String picture) {
            banner.setPicture(picture);
            return this;
        }

        public BannerBuilder categoryId(int categoryId) {
            banner.setCategoryId(categoryId);
            return this;
        }

        public Banner build() {
            return banner;
        }
    }
}
