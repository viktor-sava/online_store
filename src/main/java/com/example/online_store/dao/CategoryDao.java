package com.example.online_store.dao;

import com.example.online_store.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findTop5CategoriesByOrderByLastModifiedDesc(String preferableLocale);
    List<Category> findCategoriesByParentIdByOrderName(String preferableLocale, int parentId);
    //List<Category> findCategoryTreeByNameAndLocale(String name, String locale);
}
