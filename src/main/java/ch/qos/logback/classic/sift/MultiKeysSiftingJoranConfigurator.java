package ch.qos.logback.classic.sift;

import com.google.common.base.Splitter;
import lombok.val;

import java.util.Map;

public class MultiKeysSiftingJoranConfigurator extends SiftingJoranConfigurator {
    public MultiKeysSiftingJoranConfigurator(String key, String value, Map<String, String> parentPropertyMap) {
        super(key, value, parentPropertyMap);
    }

    @Override
    protected void buildInterpreter() {
        super.buildInterpreter();

        val splitter = Splitter.on(',').omitEmptyStrings().trimResults();
        val keys = splitter.splitToList(key);
        val vals = splitter.splitToList(value);

        val context = interpreter.getInterpretationContext();
        for (int i = 0, ii = keys.size(); i < ii; ++i) {
            context.addSubstitutionProperty(keys.get(i), vals.get(i));
        }
    }

}
