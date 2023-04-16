package com.allen.bai.maven;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HelloServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String str = "你好！世界！";
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    response.getWriter().write(str);
  }
}
