package com.helloworld.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloWorldEndpoint extends HttpServlet {
    public static final String URL = "/v1/helloworld";

    private static final Logger logger = Logger.getLogger(HelloWorldEndpoint.class.getSimpleName());

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("application/json; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("{\"status\":\"OK\"}");

        out.close();
    }
}
