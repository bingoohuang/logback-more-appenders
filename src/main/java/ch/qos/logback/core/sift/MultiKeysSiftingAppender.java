package ch.qos.logback.core.sift;

import ch.qos.logback.classic.sift.AppenderFactoryUsingJoran;
import ch.qos.logback.classic.sift.MultiKeysAppenderFactory;
import ch.qos.logback.classic.sift.SiftingAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

public class MultiKeysSiftingAppender extends SiftingAppender {

    @Override
    public void setAppenderFactory(AppenderFactory<ILoggingEvent> appenderFactory) {
        val factory = (AppenderFactoryUsingJoran) appenderFactory;

        super.setAppenderFactory(new MultiKeysAppenderFactory(
                factory, factory.key, factory.parentPropertyMap));
    }

    @Override protected void append(ILoggingEvent event) {
        if (this.isStarted()) {
            val discriminatingValue = getDiscriminator().getDiscriminatingValue(event);
            if (StringUtils.contains(discriminatingValue, "silent")) return;

            val timestamp = this.getTimestamp(event);
            if (eventMarksEndOfLife(event)) {
                appenderTracker.endOfLife(discriminatingValue);
            }

            appenderTracker.removeStaleComponents(timestamp);

            val appender = appenderTracker.getOrCreate(discriminatingValue, timestamp);
            appender.doAppend(event);
        }
    }
}
