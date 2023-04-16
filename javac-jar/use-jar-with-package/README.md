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


```
