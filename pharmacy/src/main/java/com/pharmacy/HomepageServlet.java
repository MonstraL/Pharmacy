package com.pharmacy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Stepan
 * on 28-Apr-18
 *
 * Simple servlet to have a homepage loaded instead of the HTTP 404 Error
 * on the server startup.
 */

public class HomepageServlet extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        message = "Pharmacy chains REST API Homepage";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy() {
    }
}
