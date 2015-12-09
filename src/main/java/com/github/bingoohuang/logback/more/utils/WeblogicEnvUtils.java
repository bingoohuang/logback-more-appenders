package com.github.bingoohuang.logback.more.utils;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;

public class WeblogicEnvUtils {
    public static String getListenPort() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
            MBeanServer server = (MBeanServer) ctx.lookup("java:comp/env/jmx/runtime");
            ObjectName service = new ObjectName("com.bea:Name=RuntimeService,"
                    + "Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
            ObjectName rt = (ObjectName) server.getAttribute(service, "ServerRuntime");

            return "" + server.getAttribute(rt, "ListenPort");
        } catch (Throwable e) {
            e.printStackTrace();
            return "9999";
        } finally {
            try {
                if (ctx != null) ctx.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
