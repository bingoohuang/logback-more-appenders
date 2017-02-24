package com.github.bingoohuang.logback.more.appenders;

import ch.qos.logback.classic.sift.MDCBasedDiscriminator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

public class MultiKeysMDCBasedDiscriminator extends MDCBasedDiscriminator {
    @Getter @Setter private String splitter = ",";

    @Override
    public String getDiscriminatingValue(ILoggingEvent event) {
        val splitter = Splitter.on(getSplitter()).omitEmptyStrings().trimResults();
        val mdcMap = event.getMDCPropertyMap();
        if (mdcMap == null) return getDefaultValue();

        val keys = splitter.split(getKey());
        val values = new StringBuilder();
        for (String key : keys) {
            String value = mdcMap.get(key);
            String str = value == null ? getDefaultValue() : value;
            values.append(str).append(getSplitter());
        }

        return StringUtils.removeEnd(values.toString(), getSplitter());

    }
}
