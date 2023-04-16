## 场景

Main.java 里面依赖了两个包。一个是 libs 目录内的 SpecialSay.java ，他定义了 package ；另一个包依赖了 use-other-jar/libs 内的 Hello.java ，它没定义 package 。

## 打包

开始练习前，删除以下文件和目录：

```
Main.class
main.jar
libs/allen
libs/special-say.jar
```

先打包 SpecialSay.java ：

```shell
# 进入 SpecialSay.java 所在的目录
$ cd javac-jar/use-jar-with-package/libs
# 编译
$ javac -d . *.java
# 打jar包
$ jar -cvf special-say.jar ./allen
```

打包 Main.java ：

```shell
# 进入 Main.java 所在目录
$ cd ../
# 编译，注意这里要将依赖的jar包通过参数-cp传进去
$ javac -cp "./libs/special-say.jar;../use-other-jar/libs/hello.jar" Main.java
# 运行class文件
$ java -cp ".;./libs/special-say.jar;../use-other-jar/libs/hello.jar" Main
# 打jar包
# 注意：这里使用了 "-C . ." ,意思将当前目录的内容打成jar包，放到当前目录下
# 打包后的jar包可以用解压文件打开看下，会发现里面jar包里包含了当前目录下的所有文件。
# 这意味着jar命令真的指是做了打包处理
# "-C" 的方式可以将多个class都打包到一起，毕竟多文件开发是很常见的
$ jar -cvfm main.jar manifest.txt -C . .
# 运行jar包
$ java -jar main.jar
```
