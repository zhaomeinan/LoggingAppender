package net.myscloud.plugin.logging.logback.redis.appender;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class LogbackRedisAppenderTest {

    @Before
    public void init() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            configurator.doConfigure(LogbackRedisAppenderTest.class.getResourceAsStream("/logback.xml"));
        } catch (JoranException je) {
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }

    @Test
    public void testRedissionList() {
        Logger logger = LoggerFactory.getLogger(LogbackRedisAppenderTest.class);
        IntStream.range(0, 100).forEach(i -> {
            logger.warn("test" + i);
        });
    }

    @After
    public void destroy() {
    }

}