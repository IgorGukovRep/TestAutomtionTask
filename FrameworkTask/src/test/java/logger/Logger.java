package logger;

import org.slf4j.LoggerFactory;

public class Logger {

    private final org.slf4j.Logger logger;

    private static final ThreadLocal<Logger> instance = new ThreadLocal<>();

    private Logger() {
        this(Logger.class);
    }

    private Logger(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public static Logger getInstance() {
        if (instance.get() == null)
            instance.set(new Logger());
        return instance.get();
    }

    public static Logger getInstance(Class clazz) {
        return new Logger(clazz);
    }

    public void info(Object message) {
        logger.info(message.toString());
    }

    public void debug(Object message) {
        logger.debug(message.toString());
    }

    public void debug(Exception e) {
        logger.debug(e.getMessage() + System.lineSeparator() + e.getStackTrace());
    }

    public void error(Object message) {
        logger.error(message.toString());
    }

    public void trace(Object message) {
        logger.trace(message.toString());
    }

    public void testStartInfo(Class clazz) {
        info(String.format("======= Test '%s' is started =======", clazz.getSimpleName()));
    }

    public void afterInfo(Object message) {
        info(message.toString());
    }
}
