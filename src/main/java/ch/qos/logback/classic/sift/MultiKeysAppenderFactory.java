package ch.qos.logback.classic.sift;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.joran.event.SaxEvent;
import ch.qos.logback.core.sift.SiftingJoranConfiguratorBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiKeysAppenderFactory extends AppenderFactoryUsingJoran {

    public MultiKeysAppenderFactory(AppenderFactoryUsingJoran appenderFactory, String key, Map<String, String> parentPropertyMap) {
        super(modify(appenderFactory.getEventList()), key, parentPropertyMap);
    }

    private static List<SaxEvent> modify(List<SaxEvent> eventList) {
        // in AbstractAppenderFactoryUsingJoran, removeSiftElement call eventList.subList(1, eventList.size() - 1)

        ArrayList<SaxEvent> arrayList = new ArrayList<SaxEvent>(eventList.size() + 2);
        arrayList.add(null);
        arrayList.addAll(eventList);
        arrayList.add(null);

        return arrayList;
    }

    @Override
    public SiftingJoranConfiguratorBase<ILoggingEvent> getSiftingJoranConfigurator(String discriminatingValue) {
        return new MultiKeysSiftingJoranConfigurator(key, discriminatingValue, parentPropertyMap);
    }

}


