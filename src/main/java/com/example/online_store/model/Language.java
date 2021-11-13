package com.example.online_store.model;

import java.io.Serializable;

public class Language implements Serializable {

    private int id;

    private String shortName;

    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public static LanguageBuilder builder() {
        return new LanguageBuilder();
    }

    public static final class LanguageBuilder {

        private final Language language;

        private LanguageBuilder() {
            language = new Language();
        }

        public LanguageBuilder id(int id) {
            language.setId(id);
            return this;
        }

        public LanguageBuilder shortName(String shortName) {
            language.setShortName(shortName);
            return this;
        }

        public LanguageBuilder fullName(String fullName) {
            language.setFullName(fullName);
            return this;
        }

        public Language build() {
            return language;
        }
    }
}
