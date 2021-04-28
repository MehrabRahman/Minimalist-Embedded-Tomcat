package com.revature.server.servlets;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getPathInfo().substring(1);
        if (fileName.equals(""))
            fileName = "index.html";

        InputStream file = getClass().getClassLoader().getResourceAsStream(fileName);
        if (file == null) {
            resp.setStatus(404);
            resp.getWriter().println("File Not Found");
            return;
        }

        String mimeType = getServletContext().getMimeType(fileName);
        resp.setContentType(mimeType);

        int length;
        byte[] buf = new byte[8192];
        while (buf != null && ((length = file.read(buf)) > 0)) {
            resp.getOutputStream().write(buf, 0, length);
        }
    }
}
