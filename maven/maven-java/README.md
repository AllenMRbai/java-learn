## 练习内容

1. 使用 maven 命令工具初始化一个简单 maven 项目。初次使用命令时，maven 会安装对应的插件。

```shell
# 创建maven 项目
$ mvn archetype:generate
```

2. 核心观察 pom.xml 内的配置信息，学习每个配置信息的意义与用处。

3. 将 junit 依赖的版本更改为 "4.12" 。因为该版本支持注解，使用更方便。

4. 学习 maven 项目的目录结构。maven 的理念：约定大于配置。

5. 创建 CalculatorTest.java，使用 junit 对 sum 方法进行测试。

## maven 常用命令

创建 maven 项目

```shell
$ mvn archetype:generate
```

编译

```shell
# 编译主程序，编译结果输出到 target/classes
$ mvn compile
# 编译测试程序，编译结果输出到 target/test-classes
$ mvn test-compile
```

删除打包产物。效果：删除 target 目录。

```shell
$ mvn clean
```
