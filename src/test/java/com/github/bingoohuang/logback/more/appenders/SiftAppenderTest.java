package com.github.bingoohuang.logback.more.appenders;

import com.github.bingoohuang.logback.more.utils.ConfigurationUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.MDC;

@Slf4j
public class SiftAppenderTest {
    @BeforeClass
    public static void beforeClass() {
        ConfigurationUtils.config("logback-sift-appender.xml");
    }

    @Test
    public void testThreads() throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                MDC.put("userId", "bingoo");
                log.trace("trace1");
                log.debug("debug1");
                log.info("info1");
                log.warn("warn1");
                log.error("error1");
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                MDC.put("userId", "huang");
                log.trace("trace2");
                log.debug("debug2");
                log.info("info2");
                log.warn("warn2");
                log.error("error2");
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
