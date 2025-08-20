package org.example.springbootcampuslovewall.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * 配置MyBatis相关设置，包括Mapper接口的扫描路径
 */
@Configuration
@MapperScan("org.example.springbootcampuslovewall.mapper")
public class MyBatisConfig {
}
