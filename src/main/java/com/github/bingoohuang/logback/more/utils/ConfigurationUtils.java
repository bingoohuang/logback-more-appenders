package com.github.bingoohuang.logback.more.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.val;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationUtils {
    public static void config(String classpathLogbackXmlFileName) {
        // assume SLF4J is bound to logback in the current environment
        val context = (LoggerContext) LoggerFactory.getILoggerFactory();

        try {
            val configurator = new JoranConfigurator();
            configurator.setContext(context);
            // Call context.reset() to clear any previous configuration, e.g. default
            // configuration. For multi-step configuration, omit calling context.reset().
            context.reset();

            val configStream = classpathResource(classpathLogbackXmlFileName);
            if (configStream == null)
                throw new RuntimeException(classpathLogbackXmlFileName + " is not found");

            configurator.doConfigure(configStream);
            configStream.close();
        } catch (JoranException je) {
            // StatusPrinter will handle this
        } catch (IOException e) {
            // ignore
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }

    public static InputStream classpathResource(String resourceName) {
        return ConfigurationUtils.class.getClassLoader().getResourceAsStream(resourceName);
    }
}
