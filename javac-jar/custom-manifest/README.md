## 背景

`jar -cvf hello.jar Hello.class` 命令打的 jar 包会帮我们自动创建 MANIFEST.MF 文件，但文件内的信息不包含 `Main-Class: Hello` (入口类的配置信息)，这导致 `java -jar hello.jar` 启动命令会报错。（可参考 javac-jar/simple-jar）。

## 自定义 Manifest

首先我们创建 manifest.txt 文件，包含以下信息：

```txt
Built-By: allen bai
Main-Class: Hello

```

注意后面要加一行空行。然后执行以下命令：

```shell
# 进入目录（ps：记得先删除 hello.jar，方便接下来的演示）
$ cd javac-jar/custom-manifest
# 打jar包
# 注：c表示要创建一个新的jar包，f表示给生成的jar包命名，m表示包含指定清单文件中的信息（既包含我们自定义的manifest.txt）
$ jar -cfm hello.jar manifest.txt Hello.class
# 运行jar包
$ java -jar hello.jar
```
