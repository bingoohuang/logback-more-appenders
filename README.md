# logback-more-appenders
more appenders for logback

## MultiKeysSiftingAppender

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <appender name="SIFT" class="com.github.bingoohuang.logback.more.appenders.MultiKeysSiftingAppender">
        <discriminator class="com.github.bingoohuang.logback.more.appenders.MultiKeysMDCBasedDiscriminator">
            <key>userId,userType</key>
            <defaultValue>unknown</defaultValue>
        </discriminator>
        <sift>
            <appender name="FILE-${userType}-${userId}" class="ch.qos.logback.core.FileAppender">
                <file>log-${userType}-${userId}.log</file>
                <append>false</append>
                <layout class="ch.qos.logback.classic.PatternLayout">
                    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36}:%L - %m%n</pattern>
                </layout>
            </appender>
        </sift>
    </appender>

    <root level="debug">
        <appender-ref ref="SIFT"/>
    </root>
</configuration>

```

## MaskPatternLayout for mask credit card number.

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <appender name="Console1" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="com.github.bingoohuang.logback.more.layout.MaskPatternLayout">
                <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - Console1: %m%n</pattern>
                <regexes><![CDATA[(\d{6})\d{6}(\d{3,5}[xX]?) (\d)\d{3}(\d)]]></regexes>
                <masks>$1++++++$2 $1***$2</masks>
            </layout>
        </encoder>
    </appender>
    <appender name="Console2" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - Console2: %replace(%m){'(\d{6})\d{6}(\d{3,5}[xX]?)', '$1++++++$2'}%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="Console1"/>
        <appender-ref ref="Console2"/>
    </root>

</configuration>

```