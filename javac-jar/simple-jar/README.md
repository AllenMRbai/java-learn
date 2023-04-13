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
# 运行上面的命令后会报错：“hello.jar中没有主清单属性”
# 这是因为jar包内的 MANIFEST.MF 内没配置入口文件
# 解决方案：
# 1. 使用压缩文件(windows系统内)打开jar包，用文本编辑器打开 META-INF/MANIFEST.MF 文件
# 2. 在 MANIFEST.MF 文件内添加配置 `Main-Class: Hello` ，注意冒号后面有个空格
# 3. 保存完毕后再运行上面的脚本即可
```
