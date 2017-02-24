package com.github.bingoohuang.logback.more.appenders;

import ch.qos.logback.core.OutputStreamAppender;
import com.github.bingoohuang.blackcat.instrument.callback.Blackcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by huangjb on 2017/2/24.
 */
public class BlackcatAppender<E> extends OutputStreamAppender<E> {
    @Override
    public void start() {
        OutputStream targetStream = new OutputStream() {
            @Override public void write(int b) throws IOException {
                // ignore
            }

            @Override
            public void write(byte b[]) throws IOException {
                Blackcat.log(new String(b));
            }
        };
        super.setOutputStream(targetStream);
        super.start();
    }
}
