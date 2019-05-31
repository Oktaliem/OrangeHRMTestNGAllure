package com.ohrm.utilities;

import org.apache.log4j.Logger;

public class Log {
    private Log() {throw new IllegalStateException("logging class"); }
    private static Logger logging = Logger.getLogger(Log.class.getName());
    public static void info (String message) {logging.info(message); }
    public static void warn (String message) { logging.warn(message); }
    public static void error (String message) {logging.error(message); }
    public static void fatal (String message) { logging.fatal(message); }
    public static void debug (String message) { logging.debug(message); }
}
