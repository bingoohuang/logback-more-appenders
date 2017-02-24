package com.github.bingoohuang.logback.more.appenders;

import com.github.bingoohuang.logback.more.utils.LogbackConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RollingMaxHistoryTest {
    @BeforeClass
    public static void beforeClass() {
        LogbackConfig.configFile("logback-rolling-maxhistory.xml");
    }

    @Test
    public void test() throws InterruptedException {
        log.info("Entering application.");

        // do something
        for (int i = 0; i < 60; ++i) {
            log.trace("这是我的第一个测试TRACE日志{}", i);
            log.debug("这是我的第一个测试DEBUG日志{}", i);
            log.info("这是我的第一个测试INFO日志{}", i);
            log.warn("这是我的第一个测试WARN日志{}", i);
            log.error("这是我的第一个测试ERROR日志{}", i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        // do someting

        log.info("Exiting application.");
    }
}
