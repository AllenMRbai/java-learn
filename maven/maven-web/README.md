## 练习内容

创建一个 maven web 项目：

```shell
# 创建 maven web项目
$ mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4
```

上面的 maven 命令创建的项目缺少 src/main/java 目录和 src/test 目录，需要我们手动创建。

在 src/main/java/com/allen/bai/maven 内创建 HelloServlet.java ，实现一个简单的 servlet ，同时在 src/main/webapp/WEB-INF/web.xml 内配置如下服务映射：

```XML
<!-- 配置servlet名称与servlet类的映射关系 -->
<servlet>
  <servlet-name>helloServlet</servlet-name>
  <servlet-class>com.allen.bai.maven.HelloServlet</servlet-class>
</servlet>
<!-- 配置servlet名称与url请求路径的映射关系 -->
<servlet-mapping>
  <servlet-name>helloServlet</servlet-name>
  <url-pattern>/helloServlet</url-pattern>
</servlet-mapping>
```

由于我们使用到了 javax.servlet ，所以我们需要在 pom.xml 内配置对应的依赖：

```XML
<!-- 如果不知道依赖具体的坐标和版本，可以访问下面这个链接进行搜索 -->
<!-- https://mvnrepository.com -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```

运行 `mvn clean package` 打包，target 目录下输出 war 包。

将 target/maven-web-1.0-SNAPSHOT 目录拷贝到 tomcat（建议用 v9 或 v8） 的 webapps 目录内，并重命名为 "maven-web"。

进入 tomcat 内的 bin 目录，命令行运行 `./startup.sh` 重启容器。

重启容器后，便可以访问服务了，尝试访问以下页面：

http://localhost:8080/maven-web/
