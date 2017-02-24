package com.github.bingoohuang.logback.more.appenders;

import com.github.bingoohuang.logback.more.utils.LogbackConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.MDC;

@Slf4j
public class ConsoleAppenderTest {
    @BeforeClass
    public static void beforeClass() {
        LogbackConfig.configFile("logback-console-appender.xml");
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

    @Test @SneakyThrows
    public void testThreads() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                MDC.put("threadName", "thread1");
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
                MDC.put("threadName", "thread2");
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
