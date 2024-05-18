## Q&A
所有零零散散，不适合在`guide`中说明的问题，都会放到这里~

### No.01 在2.1生成工程 下载依赖时报错
#### 问题描述
```
Error: Cannot download 'https://start.spring.io/starter.zip?type=maven-project&bootVersion=3.2.5&groupId=com.example&artifactId=MyDemo&name=MyDemo&version=0.0.1-SNAPSHOT&language=java&packageName=com.example.mydemo&javaVersion=21&packaging=jar&description=MyDemo&dependencies=web&dependencies=mybatis&dependencies=devtools&dependencies=lombok&dependencies=jdbc&dependencies=mysql': Connect timed out
```
#### 解决方案
网络环境不稳定，如果你在使用代理，请尝试关闭它。


### No.02 在生成工程时 IDEA 弹出 Error ❌
#### 问题描述
<img src="./images/no02_error.png" height = 300></img>
```
Error
Initialization failed for 'https://start.spring.io/'Please check URL, network and proxy settings
Error message.
Cannot download 'https://start.spring.io/': No route tohost: no further information
```

#### 原因
IDEA 无法连接 SpringBoot 官方的脚手架（用于生成工程）
#### 解决方案
重试，或者将右边页面的第一栏选项`Server URL:`修改为阿里云的脚手架网址：`https://start.aliyun.com/`


### No.03 编译运行工程时报错 "java.lang.NoSuchFieldError"
#### 问题描述
```
java: java.lang.NoSuchFieldError: Class com.sun.tools.javac.tree.JCTree$JCImport does not have member field 'com.sun.tools.javac.tree.JCTree qualid'
```
#### 解决方案
在`pom.xml`中找到依赖的配置，将 Lombok 的版本提升到 
```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>
```

### No.04 运行Demo2ApplicationTests时发生错误
#### 问题描述
```
java:org.apache.ibatis.binding.BindingException:Invalid bound statement (not found):com.example.demo2.DemoDao.getAllUsers
```
#### 解决方案
在`application.yml`中修改mybatis缩进，`mybatis:`要和`spring:`对齐
