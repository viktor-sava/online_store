package com.example.online_store.dao;

import com.example.online_store.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findTop4ProductsByOrderByRatingDesc(String preferableLocale);
    List<Product> findTop4ProductsByOrderByDiscountDesc(String preferableLocale);
    List<Product> findTop4ProductsByOrderByLastUpdatedDesc(String preferableLocale);
}
