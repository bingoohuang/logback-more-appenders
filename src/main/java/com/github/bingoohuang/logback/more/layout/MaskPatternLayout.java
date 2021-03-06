package com.github.bingoohuang.logback.more.layout;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskPatternLayout extends PatternLayout {
    @Override
    public String doLayout(ILoggingEvent event) {
        String message = super.doLayout(event);
        for (int i = 0; i < regexes.length; ++i) {
            Matcher matcher = regexes[i].matcher(message);
            if (!matcher.find()) continue;

            message = matcher.replaceAll(i < masks.length ? masks[i] : defaultMask);
            if (matchType == MatchType.first) break;
        }

        return message;
    }

    private Pattern[] regexes;
    private String[] masks;
    @Setter private String separate = " "; // 多个模式之间的正则分隔符。
    @Setter private String defaultMask = "*"; //  默认掩码
    private MatchType matchType = MatchType.first; // 匹配类型。


    /**
     * 设置匹配类型。
     *
     * @param matchType "first"或者"all"
     */
    public void setMatchType(String matchType) {
        this.matchType = MatchType.valueOf(matchType);
    }

    /**
     * 以分隔符进行分割的模式列表。
     *
     * @param regexes 正则表达式模式列表
     */
    public void setRegexes(String regexes) {
        String[] subPatterns = regexes.split(separate);
        this.regexes = new Pattern[subPatterns.length];
        for (int i = 0; i < subPatterns.length; ++i)
            this.regexes[i] = Pattern.compile(subPatterns[i]);
    }

    /**
     * 对应模式的掩码列表。
     *
     * @param masks 掩码列表
     */
    public void setMasks(String masks) {
        String[] subMasks = masks.split(separate);
        this.masks = new String[subMasks.length];
        for (int i = 0; i < subMasks.length; ++i)
            this.masks[i] = subMasks[i];
    }

    /**
     * 掩码类型。
     */
    public enum MatchType {
        /**
         * 仅对第一个匹配进行全部替换。
         */
        first,
        /**
         * 对所有匹配都进行替换。
         */
        all
    }
}
