package com.github.bingoohuang.logback.more;

import com.github.bingoohuang.logback.more.utils.LogbackConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.n3r.diamond.client.Miner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelDynamicChangeTest {
    Logger log = LoggerFactory.getLogger(LevelDynamicChangeTest.class);

    @BeforeClass
    public static void beforeClass() {
        LogbackConfig.configFile("logback-console-appender.xml");
        new Miner().getStone("some", "some"); // to startup diamond-client
    }

    /**
     * change diamond configFile group=diamond.extender,dataId=logger.levels,content=
     * org.n3r.diamond.client*=all
     * org.quartz.core.QuartzSchedulerThread=debug
     * org.quartz.core.JobRunShell=debug
     * com.github.bingoohuang.logback.more.LevelDynamicChangeTest=warn
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 20; ++i) {
            log.trace("这是我的第一个测试TRACE日志");
            log.debug("这是我的第一个测试DEBUG日志");
            log.info("这是我的第一个测试INFO日志");
            log.warn("这是我的第一个测试WARN日志");
            log.error("这是我的第一个测试ERROR日志");

            Thread.sleep(100);
        }
    }
}
