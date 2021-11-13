package com.example.online_store.dao;

import com.example.online_store.model.Banner;

import java.util.List;

public interface BannerDao {
    List<Banner> findAllBanners();
}
