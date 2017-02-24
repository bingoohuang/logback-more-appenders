package com.github.bingoohuang.logback.more.appenders;

import ch.qos.logback.core.rolling.RollingFileAppender;
import com.github.bingoohuang.logback.more.utils.WeblogicEnv;

public class WeblogicRollingFileAppender extends RollingFileAppender {
    static {
        System.setProperty("wlport", WeblogicEnv.getListenPort());
    }
}