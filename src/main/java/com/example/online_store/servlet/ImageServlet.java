package com.example.online_store.servlet;

import com.example.online_store.ApplicationProperties;
import com.example.online_store.ImageUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageServlet extends AbstractServlet {

    private final Logger log = Logger.getLogger(ImageServlet.class);

    public ImageServlet() {
        super(null);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String imageFolder = applicationProperties.getProperty("images-path");
        String filename = request.getParameter("url");
        if (filename != null) {
            File file = new File(imageFolder, filename);
            response.setHeader("Content-Type", getServletContext().getMimeType(filename));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            try {
                Files.copy(file.toPath(), response.getOutputStream());
            } catch (IOException e) {
                log.debug("Image has not been found", e);
//                Files.copy(new File("/img/"))
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = ImageUtils.saveImage(request.getPart("image"));
        System.out.println(uuid);
    }
}
