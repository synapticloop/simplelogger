package synapticloop.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SimpleLogger {
	// The log level output strings
	private static final String DEBUG = "DEBUG";
	private static final String INFO = "INFO";
	private static final String WARN = "WARN";
	private static final String ERROR = "ERROR";
	private static final String FATAL = "FATAL";

	// the date format
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// the number of characters to pad
	private static int MAX_CHARS = 0;

	// the logging format - this updates the format on every addition to the logger
	private static final String LOG_FORMAT_BASE = "%s [ %5s ] [ %MAX_CHARSs ] %s\n";
	private static final String LOG_FORMAT_EXCEPTION_BASE = "%s [ %5s ] [ %MAX_CHARSs ] %s, exception was: %s\n";

	private static String LOG_FORMAT = "%s [ %5s ] [ %0s ] %s\n";;
	private static String LOG_FORMAT_EXCEPTION = "%s [ %5s ] [ %0s ] %s, exception was: %s\n";

	// whether to log specific levels lookup
	private static Map<String, Boolean> logLevels = new HashMap<String, Boolean>();
	static {
		logLevels.put(DEBUG, true);
		logLevels.put(INFO, true);
		logLevels.put(WARN, true);
		logLevels.put(ERROR, true);
		logLevels.put(FATAL, true);
	}

	// the output stream to use - defaults to System.out
	private static OutputStream outputStream = System.out;

	// the name of this component
	private String component = null;

	/**
	 * Create a new Simple Logger
	 * 
	 * @param component the component name to log for
	 */
	private SimpleLogger(String component) {
		this.component = component;
	}

	/**
	 * Get a new simple logger for the named component
	 * 
	 * @param component the name of the component to log for
	 * 
	 * @return The SimpleLogger instance for this named component
	 */
	public static SimpleLogger getLogger(String component) {
		initialise(component);
		return(new SimpleLogger(component));
	}

	/**
	 * Get a logger with the defined component name
	 * 
	 * @param clazz The class from which the component name will be derived
	 * 
	 * @return The simple logger instance
	 */
	public static SimpleLogger getLogger(Class<?> clazz) {
		String component = clazz.getCanonicalName();
		initialise(component);
		return(new SimpleLogger(component));
	}

	/**
	 * initialise the Simple Logger which updates the logging format
	 * 
	 * @param component the name of the additional component
	 */
	private static synchronized void initialise(String component) {
		int length = component.length();
		if(length > MAX_CHARS) {
			MAX_CHARS = length;
			LOG_FORMAT = LOG_FORMAT_BASE.replaceAll("MAX_CHARS", Integer.toString(MAX_CHARS));
			LOG_FORMAT_EXCEPTION = LOG_FORMAT_EXCEPTION_BASE.replaceAll("MAX_CHARS", Integer.toString(MAX_CHARS));
		}
	}

	/**
	 * Output a debug level message
	 * 
	 * @param message the message to output
	 */
	public void debug(String message) { log(DEBUG, message, null); }

	/**
	 * Output a debug level message with an exception
	 * 
	 * @param message the message to output
	 * @param throwable the exception (with a stacktrace)
	 */
	public void debug(String message, Throwable throwable) { log(DEBUG, message, throwable); }

	/**
	 * output an info level message
	 * 
	 * @param message the message to output
	 */
	public void info(String message) { log(INFO, message, null); }

	/**
	 * output an info level message with an exception
	 * 
	 * @param message the message to output
	 * @param throwable the exception to output (with a stack trace)
	 */
	public void info(String message, Throwable throwable) { log(INFO, message, throwable); }

	/**
	 * output a warn level message with an exception
	 * 
	 * @param message the message to output
	 */
	public void warn(String message) { log(WARN, message, null); }

	/**
	 * output a warn level message with an exception
	 * 
	 * @param message the message to output
	 * @param throwable the exception to output (with a stack trace)
	 */
	public void warn(String message, Throwable throwable) { log(WARN, message, throwable); }

	/**
	 * output an error level message with an exception
	 * 
	 * @param message the message to output
	 */
	public void error(String message) { log(ERROR, message, null); }

	/**
	 * output an error level message with an exception
	 * 
	 * @param message the message to output
	 * @param throwable the exception to output (with a stack trace)
	 */
	public void error(String message, Throwable throwable) { log(ERROR, message, throwable); }

	/**
	 * output a fatal level message with an exception
	 * 
	 * @param message the message to output
	 */
	public void fatal(String message) { log(FATAL, message, null); }

	/**
	 * output a fatal level message with an exception
	 * 
	 * @param message the message to output
	 * @param throwable the exception to output (with a stack trace)
	 */
	public void fatal(String message, Throwable throwable) { log(FATAL, message, throwable); }

	/**
	 * log the message with
	 * 
	 * @param level the level of the message
	 * @param message the message to log
	 * @param throwable the throwable, if null, the error will nto include the throwable.getMessage()
	 */
	private void log(String level, String message, Throwable throwable) {
		if(!logLevels.get(level)) {
			return;
		}

		try {
			String currentDateTime = SIMPLE_DATE_FORMAT.format(new Date(System.currentTimeMillis()));
			if(null != throwable) {
				outputStream.write((String.format(LOG_FORMAT_EXCEPTION , currentDateTime, level, component, message, throwable.getMessage())).getBytes());
				throwable.printStackTrace(new PrintStream(outputStream));
			} else {
				outputStream.write((String.format(LOG_FORMAT, currentDateTime, level, component, message)).getBytes());
			}
		} catch(IOException ex) {
			System.out.println("COULD NOT LOG");
			ex.printStackTrace();
		}
	}

	/**
	 * Set the output stream to log to - by default this is System.out
	 * 
	 * @param outputStream The output stream to log to
	 */
	public static void setOutputStream(OutputStream outputStream) { SimpleLogger.outputStream = outputStream; }

	/**
	 * Set <strong>globally</strong> whether to log debug messages
	 * 
	 * @param shouldLog whether to log messages for this level
	 */
	public static void setShouldLogDebug(boolean shouldLog) { logLevels.put(DEBUG, shouldLog); }

	/**
	 * Set <strong>globally</strong> whether to log info messages
	 * 
	 * @param shouldLog whether to log messages for this level
	 */
	public static void setShouldLogInfo(boolean shouldLog) { logLevels.put(INFO, shouldLog); }

	/**
	 * Set <strong>globally</strong> whether to log warn messages
	 * 
	 * @param shouldLog whether to log messages for this level
	 */
	public static void setShouldLogWarn(boolean shouldLog) { logLevels.put(WARN, shouldLog); }

	/**
	 * Set <strong>globally</strong> whether to log error messages
	 * 
	 * @param shouldLog whether to log messages for this level
	 */
	public static void setShouldLogError(boolean shouldLog) { logLevels.put(ERROR, shouldLog); }

	/**
	 * Set <strong>globally</strong> whether to log fatal messages
	 * 
	 * @param shouldLog whether to log messages for this level
	 */
	public static void setShouldLogFatal(boolean shouldLog) { logLevels.put(FATAL, shouldLog); }

}