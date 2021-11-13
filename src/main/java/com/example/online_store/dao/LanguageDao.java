package com.example.online_store.dao;

import com.example.online_store.model.Language;

import java.util.List;

public interface LanguageDao {
    List<Language> findAllLanguages();
}
