package org.campuswall.springbootcampuslovewall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
    com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration.class
})
@MapperScan("org.campuswall.springbootcampuslovewall.mapper")
public class SpringbootCampusLoveWallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCampusLoveWallApplication.class, args);
    }

}
