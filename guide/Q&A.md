## Q&A

### 在2.1生成工程 下载依赖时报错
#### 问题描述
```
Error: Cannot download 'https://start.spring.io/starter.zip?type=maven-project&bootVersion=3.2.5&groupId=com.example&artifactId=MyDemo&name=MyDemo&version=0.0.1-SNAPSHOT&language=java&packageName=com.example.mydemo&javaVersion=21&packaging=jar&description=MyDemo&dependencies=web&dependencies=mybatis&dependencies=devtools&dependencies=lombok&dependencies=jdbc&dependencies=mysql': Connect timed out
```
#### 解决方案
网络环境不稳定，如果你在使用代理，请尝试关闭它。