package com.example.online_store.servlet;

import com.example.online_store.dao.BannerDao;
import com.example.online_store.dao.CategoryDao;
import com.example.online_store.dao.DAOFactory;
import com.example.online_store.dao.ProductDao;
import com.example.online_store.model.Language;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class IndexServlet extends AbstractServlet {

    private CategoryDao categoryDao;

    private ProductDao productDao;

    private BannerDao bannerDao;

    private final Logger log = Logger.getLogger(IndexServlet.class);

    public IndexServlet() {
        super("pages/index.jsp");
    }

    @Override
    public void init() {
        DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute("daoFactory");
        this.categoryDao = daoFactory.getCategoryDao();
        this.productDao = daoFactory.getProductDao();
        this.bannerDao = daoFactory.getBannerDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("categories", categoryDao.findTop5CategoriesByOrderByLastModifiedDesc(getLocale(req, resp)));
        req.setAttribute("popularGoods", productDao.findTop4ProductsByOrderByRatingDesc(getLocale(req, resp)));
        req.setAttribute("flashDeals", productDao.findTop4ProductsByOrderByDiscountDesc(getLocale(req, resp)));
        req.setAttribute("newArrivals", productDao.findTop4ProductsByOrderByLastUpdatedDesc(getLocale(req, resp)));
        req.setAttribute("banners", bannerDao.findAllBanners());

        forward(req, resp);

    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        if (lang != null) {
            List<Language> languages = (List<Language>) getServletContext().getAttribute("languages");
            if (languages.stream().map(Language::getShortName).anyMatch(p -> p.equals(lang))) {
                resp.addCookie(new Cookie("locale", lang.toUpperCase()));
            }
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/" + getReferer(req));
    }

}
