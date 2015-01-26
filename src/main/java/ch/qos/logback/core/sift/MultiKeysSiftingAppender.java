package ch.qos.logback.core.sift;

import ch.qos.logback.classic.sift.AppenderFactoryUsingJoran;
import ch.qos.logback.classic.sift.MultiKeysAppenderFactory;
import ch.qos.logback.classic.sift.SiftingAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MultiKeysSiftingAppender extends SiftingAppender {

    @Override
    public void setAppenderFactory(AppenderFactory<ILoggingEvent> appenderFactory) {
        AppenderFactoryUsingJoran appenderFactoryUsingJoran = (AppenderFactoryUsingJoran) appenderFactory;

        super.setAppenderFactory(new MultiKeysAppenderFactory(
                appenderFactoryUsingJoran,
                appenderFactoryUsingJoran.key,
                appenderFactoryUsingJoran.parentPropertyMap));
    }
}