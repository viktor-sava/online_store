package com.example.online_store.dao.mysql;

public class Queries {

    public static final class Account {

        public static final String FIND_BY_EMAIL = "SELECT * FROM account WHERE email = ? OR phone = ?";
        public static final String SAVE = "INSERT INTO account (surname, name, patronymic, email, phone, password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

    }

    public static final class Category {

        public static final String FIND_TOP_5_BY_LOCALE_BY_ORDER_BY_LAST_MODIFIED_DESC =
                "SELECT c.id, chl.name, c.picture, c.modified_at FROM category c " +
                "JOIN category_has_language chl on c.id = chl.category_id " +
                "JOIN language l on chl.language_id = l.id " +
                "WHERE l.short_name = ? " +
                "UNION ALL " +
                "SELECT c.id, chl.name, c.picture, c.modified_at FROM category c " +
                "JOIN category_has_language chl on c.id = chl.category_id " +
                "JOIN language l on chl.language_id = l.id " +
                "WHERE l.short_name = ? AND NOT EXISTS( " +
                "SELECT 1 FROM category c2 " +
                "JOIN category_has_language chl2 on c2.id = chl2.category_id " +
                "JOIN language l2 on chl2.language_id = l2.id " +
                "WHERE l2.short_name IN (?) AND c2.id = c.id) ORDER BY modified_at DESC LIMIT 5";
        public static final String FIND_CATEGORIES_BY_PARENT_ID_BY_ORDER_NAME =
                "SELECT c.id, chl.name, c.picture, c.modified_at FROM category c " +
                        "JOIN category_has_language chl on c.id = chl.category_id " +
                        "JOIN language l on chl.language_id = l.id " +
                        "WHERE l.short_name = ? AND parent_id = ? " +
                        "UNION ALL " +
                        "SELECT c.id, chl.name, c.picture, c.modified_at FROM category c " +
                        "JOIN category_has_language chl on c.id = chl.category_id " +
                        "JOIN language l on chl.language_id = l.id " +
                        "WHERE l.short_name = ? AND parent_id = ?  AND NOT EXISTS( " +
                        "SELECT 1 FROM category c2 " +
                        "JOIN category_has_language chl2 on c2.id = chl2.category_id " +
                        "JOIN language l2 on chl2.language_id = l2.id " +
                        "WHERE l2.short_name IN (?) AND parent_id = ? AND c2.id = c.id) ORDER BY name";
        public static final String FIND_CATEGORY_TREE_BY_NAME_AND_LOCALE =
                "WITH RECURSIVE cte (id, name, parent_id, short_name) AS (" +
                "SELECT category.id, name, parent_id, l.short_name FROM category " +
                "JOIN category_has_language ON category.id = category_has_language.category_id " +
                "JOIN language l ON l.id = category_has_language.language_id " +
                "WHERE name = ? AND l.short_name = ? " +
                "UNION ALL " +
                "SELECT c.id, chl.name, c.parent_id, l2.short_name FROM category c " +
                "JOIN category_has_language chl ON c.id = chl.category_id " +
                "JOIN language l2 ON chl.language_id = l2.id " +
                "JOIN cte ON c.parent_id = cte.id AND l2.short_name = cte.short_name " +
                ")" +
                "SELECT id, name, parent_id FROM cte";

    }

    public static final class Language {
        public static final String FIND_ALL = "SELECT * FROM language";
    }

    public static final class Product {
        public static final String FIND_ALL =
                "SELECT p.id, phl.name, phl.description, p.picture, p.price, p.amount, p.discount, " +
                "p.rating, p.evaluators, p.created_at, p.updated_at, p.is_active FROM product p " +
                "JOIN product_has_language phl on p.id = phl.product_id JOIN language l on l.id = phl.language_id " +
                "WHERE short_name = ? UNION ALL " +
                "SELECT p.id, phl.name, phl.description, p.picture, p.price, p.amount, p.discount, " +
                "p.rating, p.evaluators, p.created_at, p.updated_at, p.is_active FROM product p " +
                "JOIN product_has_language phl on p.id = phl.product_id JOIN language l on l.id = phl.language_id " +
                "WHERE short_name = ? AND NOT EXISTS( " +
                "SELECT 1 FROM product p2 " +
                "JOIN product_has_language phl2 ON p2.id = phl2.product_id JOIN language l on l.id = phl2.language_id " +
                "WHERE short_name IN (?) AND p2.id = p.id) ";
        public static final String FIND_4_BY_ORDER_BY_RATING_DESC =
                FIND_ALL + "ORDER BY rating / evaluators DESC LIMIT 4";
        public static final String FIND_4_BY_ORDER_BY_DISCOUNT_DESC =
                FIND_ALL + "ORDER BY discount DESC LIMIT 4";
        public static final String FIND_4_BY_ORDER_BY_LAST_UPDATED_DESC =
                FIND_ALL + "ORDER BY updated_at DESC LIMIT 4";
    }

    public static final class Banner {
        public static final String FIND_ALL = "SELECT * FROM banner";
    }

}
