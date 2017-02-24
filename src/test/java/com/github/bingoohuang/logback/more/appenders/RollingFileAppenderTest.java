package com.github.bingoohuang.logback.more.appenders;

import com.github.bingoohuang.logback.more.utils.ConfigurationUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

@Slf4j
public class RollingFileAppenderTest {
    @BeforeClass
    public static void beforeClass() {
        ConfigurationUtils.config("logback-rolling-file-appender.xml");
    }

    @Test
    public void test() {
        log.info("Entering application.");

        // do something

        log.trace("这是我的第一个测试TRACE日志");
        log.debug("这是我的第一个测试DEBUG日志");
        log.info("这是我的第一个测试INFO日志");
        log.warn("这是我的第一个测试WARN日志");
        log.error("这是我的第一个测试ERROR日志");

        // do someting

        log.info("Exiting application.");
    }
}
