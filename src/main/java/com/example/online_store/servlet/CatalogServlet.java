package com.example.online_store.servlet;

import com.example.online_store.dao.CategoryDao;
import com.example.online_store.model.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CatalogServlet extends AbstractServlet {

    private CategoryDao categoryDao;

    public CatalogServlet() {
        super("pages/catalog.jsp");
    }

    @Override
    public void init() {
        categoryDao = getDaoFactory().getCategoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!addCategories(req, resp)) {
            resp.sendRedirect(getServletContext().getContextPath() + "/category?id=" + req.getParameter("id"));
            return;
        }
        forward(req, resp);
    }

    private boolean addCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Category> categoryList;
        if (req.getParameter("id") == null) {
            categoryList = categoryDao.findCategoriesByParentIdByOrderName(getLocale(req, resp), 0);
        } else {
            categoryList = categoryDao.findCategoriesByParentIdByOrderName(getLocale(req, resp), Integer.parseInt(req.getParameter("id")));
        }
        if (categoryList.size() == 0) {
            return false;
        }
        req.setAttribute("categories", categoryList);
        return true;
    }


}
