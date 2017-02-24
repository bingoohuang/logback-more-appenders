package com.github.bingoohuang.logback.more.utils;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;

public class WeblogicEnv {
    @SneakyThrows
    public static String getListenPort() {
        @Cleanup val ctx = new InitialContext();
        val server = (MBeanServer) ctx.lookup("java:comp/env/jmx/runtime");
        val service = new ObjectName("com.bea:Name=RuntimeService,"
                + "Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
        val rt = (ObjectName) server.getAttribute(service, "ServerRuntime");

        return String.valueOf(server.getAttribute(rt, "ListenPort"));
    }
}
