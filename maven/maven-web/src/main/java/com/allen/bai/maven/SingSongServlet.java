package com.allen.bai.maven;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
// 注意这个包是我们在 maven-java 内开发并发布到 maven 本地仓库的jar包
import com.allen.bai.maven.Calculator;

public class SingSongServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Calculator c = new Calculator();
    String str = "<h3>唱一首歌给你听</h3>";
    String songTime = "<div>歌曲长度" + c.sum(4, 4) + "分钟</div>";

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    response.getWriter().write(str + songTime);
  }
}
