
A small (~9k) Simple Logger - one class,, no dependencies 

For when you really only need the simplest of loggers.

# Usage

Include the dependency (see below), instantiate the logger, and start logging...

```
import synapticloop.util.simplelogger.Logger;

...

private static final Logger LOGGER = Logger.getLogger(SimpleLoggerTest.class);

// then you can log information
LOGGER.debug("message");
LOGGER.info("message");
LOGGER.warn("message");
LOGGER.error("message");
LOGGER.fatal("message");

// or throwable exceptions as well
LOGGER.debug("message", exception);
LOGGER.warn("message", exception);
LOGGER.info("message", exception);
LOGGER.error("message", exception);
LOGGER.fatal("message", exception);
```

## Configuration

You may set logging levels globally, either through the static methods, or by including a `simplelogger.properties` file at the root of the classpath

### Static methods

```
LOGGER.setShouldLogDebug(true);
LOGGER.setShouldLogInfo(true);
LOGGER.setShouldLogWarn(true);
LOGGER.setShouldLogError(true);
LOGGER.setShouldLogFatal(true);
```

### `simplelogger.properties`

