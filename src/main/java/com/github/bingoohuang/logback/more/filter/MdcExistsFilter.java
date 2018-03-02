package com.github.bingoohuang.logback.more.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.Data;
import lombok.val;

@Data
public class MdcExistsFilter extends Filter<ILoggingEvent> {
    private String key;

    @Override public FilterReply decide(ILoggingEvent event) {
        val map = event.getMDCPropertyMap();

        return map.containsKey(key) ? FilterReply.NEUTRAL : FilterReply.DENY;
    }
}
