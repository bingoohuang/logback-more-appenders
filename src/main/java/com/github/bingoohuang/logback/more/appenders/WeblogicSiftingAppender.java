package com.github.bingoohuang.logback.more.appenders;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.bingoohuang.logback.more.utils.WeblogicEnv;
import org.slf4j.MDC;

/**
 * Append the listenPort of current weblogic server to fileName.
 */
public class WeblogicSiftingAppender extends MultiKeysSiftingAppender {

    @Override
    public long getTimestamp(ILoggingEvent event) {
        MDC.put("wlport", WeblogicEnv.getListenPort());
        return super.getTimestamp(event);
    }
}
