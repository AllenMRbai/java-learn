## 最简单的 jar 包

```shell
# 进入目录（ps：记得先删除Hello.class 和 hello.jar，方便接下来的演示）
$ cd javac-jar/simple-jar
# 编译
$ javac Hello.java
# 打jar包
$ jar -cvf hello.jar Hello.class
# 运行jar包
$ java -jar hello.jar
```
