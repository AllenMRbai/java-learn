## 使用 javac 将 src 内的 java 文件编译输出到 out 目录内

本案例演示了：

- Hello.java 和 SuperHello.java 使用了 package
- Hello.java 内使用 SuperHello ，它不需要 import ，因为他们属于同一个 package
- Main.java 内使用 Hello.java 需要 import ，因为它们不属于一个 package
- javac 的 `-d <path>` 参数能将编译结果输出到目标目录下，同时会将 class 文件放置到 package 对应的目录路径下

```shell
# 进入src目录
$ cd javac-jar/package-pra/src
# 编译，并且将结果输出到out目录下（ps： 先输出out目录，然后再执行下面的编译脚本）
$ javac -d ../out  *.java
# 进入out目录
$ cd ../out
# 执行
$ java Main
```

## 参考

- https://www.runoob.com/java/java-package.html
- https://www.runoob.com/w3cnote/java-compile-with-package.html
