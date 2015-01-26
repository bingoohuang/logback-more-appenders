package com.github.bingoohuang.logback.more.layout;

import com.github.bingoohuang.logback.more.utils.ConfigurationUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaskPatternLayoutTest {
    Logger log = LoggerFactory.getLogger(MaskPatternLayoutTest.class);

    @BeforeClass
    public static void beforeClass() {
        ConfigurationUtils.config("logback-console-mask-layout.xml");
    }

    @Test
    public void test() {
        log.info("我的18位身份证号码：12345612345678123X, 我的15位身份证号码：123456123456123");
        log.info("我的工作证号码：60476");
    }
}
