# 使用官方 OpenJDK 17 镜像作为基础镜像
FROM openjdk:17-jdk-slim

# 设置维护者信息
LABEL maintainer="CampusLoveWall Team"

# 设置工作目录
WORKDIR /app

# 复制 Maven 依赖文件
COPY pom.xml .

# 复制源代码
COPY src ./src

# 构建应用
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

# 暴露端口
EXPOSE 8080

# 运行应用
ENTRYPOINT ["java", "-jar", "/app/target/SpringbootCampusLoveWall-0.0.1-SNAPSHOT.jar"]
