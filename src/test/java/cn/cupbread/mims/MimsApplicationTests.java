package cn.cupbread.mims;

import cn.cupbread.mims.Util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MimsApplicationTests {

    @Autowired
    private RedisUtil redisUtil;


    @Test
    void contextLoads() {
        redisUtil.set("123","梁非凡");
    }

}
