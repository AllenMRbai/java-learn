## 练习内容

1. 使用 maven 命令工具初始化一个简单 maven 项目。初次使用命令时，maven 会安装对应的插件。

```shell
# 创建 maven 项目
$ mvn archetype:generate
```

2. 核心观察 pom.xml 内的配置信息，学习每个配置信息的意义与用处。

3. 将 junit 依赖的版本更改为 "4.12" 。因为该版本支持注解，使用更方便。

4. 学习 maven 项目的目录结构。maven 的理念：约定大于配置。

5. 创建 CalculatorTest.java，使用 junit 对 sum 方法进行测试。

## maven 常用命令

### 创建 maven 项目

```shell
$ mvn archetype:generate
```

### 编译

```shell
# 编译主程序，编译结果输出到 target/classes
$ mvn compile
# 编译测试程序，编译结果输出到 target/test-classes
$ mvn test-compile
```

### 删除打包产物

效果：删除 target 目录。

```shell
$ mvn clean
```

### 测试

效果：编译主程序和测试程序，同时运行测试程序将结果输出到 target/surefire-reports 内

```shell
$ mvn test
```

### 打包

根据 pom.xml 内 packaging 的配置，打包成 jar / war 包，输出到 target 目录下。

```shell
$ mvn package
```

### 安装

效果：将本地构建过程生成的 jar 包存入 Maven 本地仓库

```shell
$ mvn install
```
