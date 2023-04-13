## 场景

假设 Demo.java 是我们的入口文件，我们要引入 libs/hello.jar 包。同时我们要将 Demo.class 打成 jar 包

## 练习

### 提前准备

先删除以下几个文件，方便下面的练习：

```
demo.jar
Demo.class
libs/hello.jar
libs/hello.class
```

### 准备 hello.jar

我们先准备好 libs/hello.jar :

```shell
# 进入libs目录
$ cd javac-jar/use-other-jar/libs
# 编译
$ javac Hello.java
# 打jar包
$ jar -cvf hello.jar Hello.class
```

### 打包 demo.jar

```shell
# 回到 use-other-jar 目录
$ cd ../
# 编译
# 注意事项：我们需要加上 -cp 用来配置我们所依赖jar包的classpath
$ javac -cp ./libs/hello.jar Demo.java
# 尝试运行下:
# 注意事项1：运行的时候也要加上 -cp ,告诉虚拟机我们所依赖jar包的classpath。
# 注意事项2：我们要加入当前运行目录的路径"."。如果有多个路径用";"分开（windows内是";",mac内印象是用":"分割的）。
# 注意事项2：-cp 后的配置项最好用引号包起来作为一个字符串，避免路径配置中的某些符合在shell中有其他功能导致命令执行失败
$ java -cp ".;./libs/hello.jar" Demo
# 打jar包
# 注意事项：我们需要将manifest.txt内的配置加到jar包内META-INF/MANIFEST.MF，所以这里用了 m。
# manifest.txt 文件内最核心的是需要配置 Class-path 配置上。
$ jar -cvfm demo.jar manifest.txt Demo.class
# 运行jar包
# 因为jar包META-INF/MANIFEST.MF文件内配置了 Class-path ，所以我们执行java 命令时不再需要 -cp。
$ java -jar demo.jar
```

## 参考

- https://www.cnblogs.com/mq0036/p/8566427.html#a11
- https://blog.51cto.com/mouday/5055807
- https://www.baeldung.com/java-jar-manifest
- https://blog.csdn.net/gao_zhennan/article/details/112749742
