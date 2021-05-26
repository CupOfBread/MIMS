package cn.cupbread.mims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"cn.cupbread.mims.DAO"})
public class MimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MimsApplication.class, args);
    }

}
