package cn.cupbread.mims.Component;

import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author : CupOfBread
 * @version : 0.1.0
 * @date : 2021/05/24
 * @description :
 */

@Slf4j
@Component
public class StartedTips implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
//        log.info("====================================================");
//        log.info("\n" + SystemUtil.getJavaRuntimeInfo());
//        log.info("\n" + SystemUtil.getRuntimeInfo());
        log.info("=====================项目启动完毕=====================");

    }
}
