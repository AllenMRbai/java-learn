## pom 的继承

使用 `mvn archetype:generate` 创建项目，设置 pom.xml 内的 packaging 为 pom ，表示当前项目为父工程。

进入 maven-parent 项目目录，使用 `mvn archetype:generate` 创建 3 个子工程（分别为 maven-module maven-module-2 maven-module-3）。创建完毕后，打开父工程的 pom.xml 会看到如下的配置：

```XML
<modules>
  <module>maven-module</module>
  <module>maven-module-2</module>
  <module>maven-module-3</module>
</modules>
```

同时打开子工程的 pom.xml ，可以看到父工程的相关配置：

```XML
<parent>
  <groupId>com.allen.bai.maven</groupId>
  <artifactId>maven-parent</artifactId>
  <version>1.0-SNAPSHOT</version>
</parent>
```

这里有个知识点，如果子工程的 groupId 和 version 和父工程一样的话，子工程 pom.xml 内是可以省略的。

父工程的优势是可以统一管理依赖的版本，我们只需要在父工程 pom.xml 内添加 dependencyManagement 配置即可：

```XML
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.0.0.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.0.0.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.0.0.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>4.0.0.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>4.0.0.RELEASE</version>
    </dependency>
  </dependencies>
</dependencyManagement>
```

观察上面的配置我们会发现这几个依赖的 version 都是一样的，我们可以使用 pom 的自定义属性来将它们统一管理起来。首先，我们在父工程 pom.xml 的 properties 内配置自定义的属性：

```XML
<allen.spring.version>4.0.0.RELEASE</allen.spring.version>
```

然后将 `<version>4.0.0.RELEASE</version>` 都替换成 `<version>${allen.spring.version}</version>` 即可。

子工程使用被父工程统一管理的依赖时，只需要再自己的 pom.xml 内引入即可，同时忽略掉 version 的配置即可，例子：

```XML
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
    </dependency>
  </dependencies>
```
