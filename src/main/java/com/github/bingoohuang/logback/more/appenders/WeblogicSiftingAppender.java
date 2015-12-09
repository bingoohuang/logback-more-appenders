package com.github.bingoohuang.logback.more.appenders;

import ch.qos.logback.classic.sift.SiftingAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.bingoohuang.logback.more.utils.WeblogicEnvUtils;
import org.slf4j.MDC;

/**
 * Append the listenPort of current weblogic server to fileName.
 */
public class WeblogicSiftingAppender extends SiftingAppender {

    @Override
    public long getTimestamp(ILoggingEvent event) {
        MDC.put("wl-port", WeblogicEnvUtils.getListenPort());
        return event.getTimeStamp();
    }
}
