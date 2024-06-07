## 如何运行打包后的程序

### 配置环境和打包
- 确保你安装了`JDK`，并可以在终端(`PowerShell`、`cmd`)执行`java`命令。
- 在我们仓库的 Release 上下载 Jar 包，或者自行打包我们的项目。

### 配置数据库
- 运行你的 MySQL ，在`demo`数据库下执行我们仓库提供的建表 SQL `database\database_model.sql`
- 我们也提供了测试数据的插入 SQL ，可在同目录下找到。

### 启动程序
打开终端，在 Jar 包所在目录下，执行命令：
```bash
 java -jar <jar包名> --spring.datasource.password=<你的密码>
```
看到以下输出，说明程序启动成功：
```log
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.5)

2024-06-07T12:24:56.272+08:00  INFO 68036 --- [           main] c.yuxeng.display.DemoDisplayApplication  : Starting DemoDisplayApplication v0.0.1-SNAPSHOT using Java 22.0.1 with PID 68036 (E:\Documents\CodeField\SpringBoot-MyBatis-Example\demo_display\target\display-0.0.1-SNAPSHOT.jar started by 19179 in E:\Documents\CodeField\SpringBoot-MyBatis-Example\demo_display\target)
2024-06-07T12:24:56.274+08:00  INFO 68036 --- [           main] c.yuxeng.display.DemoDisplayApplication  : No active profile set, falling back to 1 default profile: "default"
2024-06-07T12:24:57.601+08:00  INFO 68036 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-06-07T12:24:57.612+08:00  INFO 68036 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-06-07T12:24:57.612+08:00  INFO 68036 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-06-07T12:24:57.643+08:00  INFO 68036 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-06-07T12:24:57.644+08:00  INFO 68036 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1310 ms
2024-06-07T12:24:58.811+08:00  INFO 68036 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 1 endpoint(s) beneath base path '/actuator'
2024-06-07T12:24:58.873+08:00  INFO 68036 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-06-07T12:24:58.883+08:00  INFO 68036 --- [           main] c.yuxeng.display.DemoDisplayApplication  : Started DemoDisplayApplication in 3.097 seconds (process running for 3.554)
```

### 通过 Apifox 在本地调用接口
以上服务端程序启动后，可在 Apifox 调用本地接口进行开发和测试。
- [🤖Apifox 接口调用](https://apifox.com/apidoc/shared-feba2b4b-6cab-40fc-9309-ce4bc9dd8dc6)
  
> 注意⚠️：在网页端调用本地接口需要安装 Chrome 插件，使用Chrome 插件 Agent
