package com.example.online_store.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product implements Serializable {

    private int id;

    private String name;

    private String description;

    private String picture;

    private BigDecimal price;

    private Long amount;

    private Long discount;

    private Long rating;

    private Long evaluators;

    private Timestamp createDate;

    private Timestamp updateDate;

    private boolean active;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getEvaluators() {
        return evaluators;
    }

    public void setEvaluators(Long evaluators) {
        this.evaluators = evaluators;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", discount=" + discount +
                ", rating=" + rating +
                ", evaluators=" + evaluators +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", active=" + active +
                '}';
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static final class ProductBuilder {

        private final Product product;

        private ProductBuilder() {
            product = new Product();
        }

        public ProductBuilder id(int id) {
            product.setId(id);
            return this;
        }

        public ProductBuilder name(String name) {
            product.setName(name);
            return this;
        }

        public ProductBuilder description(String description) {
            product.setDescription(description);
            return this;
        }

        public ProductBuilder picture(String picture) {
            product.setPicture(picture);
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            product.setPrice(price);
            return this;
        }

        public ProductBuilder amount(Long amount) {
            product.setAmount(amount);
            return this;
        }

        public ProductBuilder discount(Long discount) {
            product.setDiscount(discount);
            return this;
        }

        public ProductBuilder rating(Long rating) {
            product.setRating(rating);
            return this;
        }

        public ProductBuilder evaluators(Long evaluators) {
            product.setEvaluators(evaluators);
            return this;
        }

        public ProductBuilder createDate(Timestamp createDate) {
            product.setCreateDate(createDate);
            return this;
        }

        public ProductBuilder updateDate(Timestamp updateDate) {
            product.setUpdateDate(updateDate);
            return this;
        }

        public ProductBuilder active(boolean active) {
            product.setActive(active);
            return this;
        }

        public Product build() {
            return product;
        }
    }
}
