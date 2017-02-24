package ch.qos.logback.classic.sift;

import lombok.val;
import org.slf4j.MDC;

import java.util.Map;

public class MultiKeysSiftingJoranConfigurator extends SiftingJoranConfigurator {
    public MultiKeysSiftingJoranConfigurator(String key, String value, Map<String, String> parentPropertyMap) {
        super(key, value, parentPropertyMap);
    }

    @Override
    protected void buildInterpreter() {
        super.buildInterpreter();

        Map<String, String> mdcPropertyMap = MDC.getMDCAdapter().getCopyOfContextMap();
        if (mdcPropertyMap == null) return;

        val interpretationContext = interpreter.getInterpretationContext();
        for (val entry : mdcPropertyMap.entrySet())
            interpretationContext.addSubstitutionProperty(entry.getKey(), entry.getValue());
    }

}
