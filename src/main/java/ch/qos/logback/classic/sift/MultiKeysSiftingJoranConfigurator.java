package ch.qos.logback.classic.sift;

import java.util.Map;

import org.slf4j.MDC;

import ch.qos.logback.core.joran.spi.InterpretationContext;

public class MultiKeysSiftingJoranConfigurator extends SiftingJoranConfigurator {
    public MultiKeysSiftingJoranConfigurator(String key, String value, Map<String, String> parentPropertyMap) {
        super(key, value, parentPropertyMap);
    }

    @Override
    protected void buildInterpreter() {
        super.buildInterpreter();

        Map<String, String> mdcPropertyMap = MDC.getMDCAdapter().getCopyOfContextMap();
        if (mdcPropertyMap == null) return;

        InterpretationContext interpretationContext = interpreter.getInterpretationContext();
        for (Map.Entry<String, String> entry : mdcPropertyMap.entrySet())
            interpretationContext.addSubstitutionProperty(entry.getKey(), entry.getValue());
    }

}
