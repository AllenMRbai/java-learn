## 练习一

目标：创建 maven web 项目，实现 servlet 并部署到 tomcat 容器内。

首先，创建一个 maven web 项目：

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

## 练习二

目标：依赖我们本地开发的 maven-java 所产出的 jar 包。

我们在 pom.xml 内添加我们自己开发并发布到 maven 本地仓库的 jar 包：

```XML
<dependency>
    <groupId>com.allen.bai.maven</groupId>
    <artifactId>maven-java</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

我们将 maven-java 内的 CalculatorTest.java 拷贝到当前项目内。运行 `mvn test` 发现能进行正常的测试，并输出测试结果。

在 SingSongServlet.java 内引入该包，并使用该包的 sum 方法。运行 `mvn clean package`，将 maven-web-1.0-SNAPSHOT 目录拷贝到 tomcat 的 webapps 目录内，并重命名为 "maven-web"。重启 tomcat 容器，访问 http://localhost:8080/maven-web/sing-song , 我们可以看到页面内出现 “歌曲长度 8 分钟”。

打开 target/maven-web-1.0-SNAPSHOT/WEB-INF/lib ，会看到我们依赖的 maven-java-1.0-SNAPSHOT.jar 。因为在 pom.xml 内 maven-web 的 scope 为 compile，maven 就会把它放到输出的 war 包内。

运行下 `mvn dependency:list` 查看当前项目的依赖，可以看到以下结果：

```shell
The following files have been resolved:
    com.allen.bai.maven:maven-java:jar:1.0-SNAPSHOT:compile
    org.hamcrest:hamcrest-core:jar:1.3:test
    javax.servlet:javax.servlet-api:jar:3.1.0:provided
    junit:junit:jar:4.12:test
```

会发现，除了 pom.xml 内定义的三个依赖（包括 junit maven-java servlet-api），还出现了 hamcrest-core 。这个包哪来的呢?

我们运行下 `mvn dependency:tree` 以树形结构查看依赖，结果：

```shell
 +- junit:junit:jar:4.12:test
 |  \- org.hamcrest:hamcrest-core:jar:1.3:test
 +- javax.servlet:javax.servlet-api:jar:3.1.0:provided
 \- com.allen.bai.maven:maven-java:jar:1.0-SNAPSHOT:compile
```

可以发现，harmcrest-core 是 junit 的依赖，也就是说当前项目继承了 junit 的依赖。

## 练习三： 依赖范围

注意：“部署到服务器” 是指打包后的 war 包内会不会包含该依赖。比如类型为 test 的 scope 依赖，很明显它只是我们本地测试用的，所以不需要把它放到 war 包内，而 compile 类型的 scope 依赖，我们在开发环节和部署环节都会用到。(注意：在 jar 包内，无论 scope 是不是 compile，都不会把 3 方依赖的 jar 包打进去，因为使用方会用 maven 去安装它，TODO 待确认)。

provided 表示该依赖在 servlet 容器内已提供，就不需再打包到 war 包内了。

maven 依赖默认为 compile 的 scope。

| 类型     | main 目录 | test 目录 | 开发过程 | 部署到服务器 |
| -------- | --------- | --------- | -------- | ------------ |
| compile  | 有效      | 有效      | 有效     | 有效         |
| test     |           | 有效      | 有效     |              |
| provided | 有效      | 有效      | 有效     |              |

## 练习四：依赖传递

一个场景，如果 A 依赖 B，B 依赖了 C，那么 A 项目内能直接用 C 吗？

结论：要看 B 中依赖的 C 的 scope 类型。如果是 compile 类型，A 可以使用 C，test 和 provided 不行。

测试方式：在 maven-java 项目 pom.xml 内添加 spring-core，`mvn install` 重新安装。然后进入 maven-web，运行`mvn dependency:tree` 和 `mvn dependency:list` 。

## 练习五：依赖排除

如下场景，A 依赖的 B 和 C 都依赖了 D，但它们版本不一样，这就导致了依赖冲突。这时候我们就希望排除掉重复依赖。

```txt
    |--> B --> D v0.1
A --|
    |--> C --> D v0.6
```

如何排除项目内的重复依赖呢？`mvn dependency:tree` 和 `mvn dependency:analyze-duplicate` 命令可以协助排查。

排除依赖的方法是在 pom.xml 内，在需要排查的依赖内添加 exclusions 配置。

例子，假设我们要排除掉 maven-java 传递给我们的 commons-logging ，可以这样配置：

```xml
<dependency>
    <groupId>com.allen.bai.maven</groupId>
    <artifactId>maven-java</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!-- compile 会把依赖jar包放到项目打的war包内 -->
    <scope>compile</scope>
    <!-- 配置要排除的依赖 -->
    <exclusions>
      <exclusion>
        <!-- 配置要排除依赖的具体坐标，不需要version -->
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
      </exclusion>
    </exclusions>
</dependency>
```

没排除前，运行 `mvn dependency:list` 结果是这样的：

```text
 commons-logging:commons-logging:jar:1.1.1:compile
 org.hamcrest:hamcrest-core:jar:1.3:test
 javax.servlet:javax.servlet-api:jar:3.1.0:provided
 junit:junit:jar:4.12:test
```

排除后是这样的：

```text
 org.hamcrest:hamcrest-core:jar:1.3:test
 javax.servlet:javax.servlet-api:jar:3.1.0:provided
 junit:junit:jar:4.12:test
```
