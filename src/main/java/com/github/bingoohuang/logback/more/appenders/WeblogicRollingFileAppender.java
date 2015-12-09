package com.github.bingoohuang.logback.more.appenders;

import ch.qos.logback.core.rolling.RollingFileAppender;
import com.github.bingoohuang.logback.more.utils.WeblogicEnvUtils;

public class WeblogicRollingFileAppender extends RollingFileAppender {
    static {
        System.setProperty("wl-port", WeblogicEnvUtils.getListenPort());
    }
}