# 校园表白墙项目

校园表白墙是一个基于Spring Boot开发的后端项目，用于构建校园内的表白交流平台。

## 目录结构

~~~
CampusLoveWall/
├── .gitignore
├── README.md
└── SpringbootCampusLoveWall/
    ├── .dockerignore
    ├── Dockerfile
    ├── docker-compose.yml
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── org/
    │   │   │       └── campuswall/
    │   │   │           └── springbootcampuslovewall/
    │   │   │               ├── SpringbootCampusLoveWallApplication.java
    │   │   │               ├── admin/
    │   │   │               │   ├── controller/
    │   │   │               │   │   └── AdminController.java
    │   │   │               │   ├── entity/
    │   │   │               │   │   └── Admin.java
    │   │   │               │   └── service/
    │   │   │               │       ├── impl/
    │   │   │               │       │   └── AdminServiceImpl.java
    │   │   │               │       └── AdminService.java
    │   │   │               ├── auth/
    │   │   │               │   ├── controller/
    │   │   │               │   │   └── AuthController.java
    │   │   │               │   └── service/
    │   │   │               │       ├── impl/
    │   │   │               │       │   ├── AdminAccountServiceImpl.java
    │   │   │               │       │   └── UserAccountServiceImpl.java
    │   │   │               │       └── AccountService.java
    │   │   │               ├── common/
    │   │   │               │   ├── config/
    │   │   │               │   │   ├── CorsConfig.java
    │   │   │               │   │   ├── JacksonConfig.java
    │   │   │               │   │   ├── JwtProperties.java
    │   │   │               │   │   ├── MyBatisConfig.java
    │   │   │               │   │   ├── RedisConfig.java
    │   │   │               │   │   └── SecurityConfig.java
    │   │   │               │   ├── core/
    │   │   │               │   │   ├── controller/
    │   │   │               │   │   │   └── BaseController.java
    │   │   │               │   │   ├── mapper/
    │   │   │               │   │   │   └── BaseMapperPlus.java
    │   │   │               │   │   ├── model/
    │   │   │               │   │   └── service/
    │   │   │               │   ├── enums/
    │   │   │               │   │   └── RoleEnum.java
    │   │   │               │   ├── exception/
    │   │   │               │   │   ├── CustomerException.java
    │   │   │               │   │   └── GlobalExceptionHandler.java
    │   │   │               │   ├── result/
    │   │   │               │   │   ├── PageResult.java
    │   │   │               │   │   ├── R.java
    │   │   │               │   │   └── ResultCodeEnum.java
    │   │   │               │   └── utils/
    │   │   │               │       ├── JwtUtil.java
    │   │   │               │       ├── MixUtils.java
    │   │   │               │       ├── RedisUtil.java
    │   │   │               │       └── TokenUtils.java
    │   │   │               ├── controller/
    │   │   │               │   └── WebController.java
    │   │   │               ├── entity/
    │   │   │               │   └── Account.java
    │   │   │               ├── mapper/
    │   │   │               │   ├── AdminMapper.java
    │   │   │               │   └── UserMapper.java
    │   │   │               ├── security/
    │   │   │               │   └── JwtAuthenticationFilter.java
    │   │   │               ├── user/
    │   │   │               │   ├── controller/
    │   │   │               │   │   └── UserController.java
    │   │   │               │   ├── entity/
    │   │   │               │   │   └── User.java
    │   │   │               │   └── service/
    │   │   │               │       ├── impl/
    │   │   │               │       └── UserService.java
    │   │   │               └── service/
    │   │   └── resources/
    │   │       └── application.yml
    │   └── test/
    │       └── java/
    │           └── org/
    │               └── campuswall/
    │                   └── springbootcampuslovewall/
    │                       └── SpringbootCampusLoveWallApplicationTests.java
    └── target/

~~~

## 技术栈

- Spring Boot 3.5.4
- Java 17
- Maven 项目管理
- MySQL 数据库
- Redis 缓存
- JWT 认证
- MyBatis Plus ORM框架
- Druid 数据库连接池

## 功能模块

1. **用户管理模块**
   - 用户注册、登录
   - 用户信息管理

2. **管理员模块**
   - 管理员登录
   - 用户内容审核

3. **认证授权模块**
   - JWT Token生成与验证
   - 权限控制

4. **表白墙核心功能**
   - 发布表白信息
   - 查看表白信息
   - 评论与互动

## 部署方式

### Docker部署（推荐）

项目提供了Docker支持，可以通过docker-compose一键部署整个应用。

1. 确保系统已安装Docker和Docker Compose
2. 在项目根目录执行以下命令：
~~~
docker-compose up -d
~~~
3. 等待构建完成，应用将在8080端口运行

### 传统部署

1. 确保系统已安装Java 17和Maven
2. 修改`src/main/resources/application.yml`配置文件中的数据库连接信息
3. 执行以下命令构建项目：
   
~~~bash 
mvn clean package -DskipTests
~~~

4. 运行应用：

~~~bash
java -jar target/SpringbootCampusLoveWall-0.0.1-SNAPSHOT.jar
~~~

## 配置说明

主要配置文件位于`src/main/resources/application.yml`：

- 服务器端口：8080
- 数据库连接：MySQL 本地连接
- Redis配置：本地Redis连接
- JWT配置：Token密钥和过期时间

## 开发环境

- JDK 17
- Maven 3.8+
- MySQL 8.0
- Redis
- IntelliJ IDEA（推荐）

## 注意事项

1. 生产环境中请修改默认的数据库密码
2. JWT密钥在生产环境中必须通过环境变量配置，不能使用默认值
3. 部署时请根据实际环境修改数据库和Redis连接配置


