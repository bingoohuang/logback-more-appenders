package com.github.bingoohuang.logback.more.appenders;

import com.github.bingoohuang.logback.more.utils.ConfigurationUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RollingGzFileAppenderTest {
    Logger log = LoggerFactory.getLogger(RollingGzFileAppenderTest.class);

    @BeforeClass
    public static void beforeClass() {
        ConfigurationUtils.config("logback-rolling-gz-file-appender.xml");
    }

    @Test
    public void test() throws InterruptedException {
        log.info("Entering application.");

        // do something

        for (int i = 0; i < 10; ++i) {
            log.trace("这是我的第一个测试TRACE日志{}", i);
            log.debug("这是我的第一个测试DEBUG日志{}", i);
            log.info("这是我的第一个测试INFO日志{}", i);
            log.warn("这是我的第一个测试WARN日志{}", i);
            log.error("这是我的第一个测试ERROR日志{}", i);
            Thread.sleep(10000);
        }

        // do someting

        log.info("Exiting application.");
    }
}
