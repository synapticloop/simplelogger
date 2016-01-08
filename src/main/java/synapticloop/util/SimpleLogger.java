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

	private static OutputStream outputStream = System.out;

	private String component = null;

	private SimpleLogger(String component) {
		this.component = component;
	}

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

	private static synchronized void initialise(String component) {
		int length = component.length();
		if(length > MAX_CHARS) {
			MAX_CHARS = length;
			LOG_FORMAT = LOG_FORMAT_BASE.replaceAll("MAX_CHARS", Integer.toString(MAX_CHARS));
			LOG_FORMAT_EXCEPTION = LOG_FORMAT_EXCEPTION_BASE.replaceAll("MAX_CHARS", Integer.toString(MAX_CHARS));
		}
	}

	public void debug(String message) { log(DEBUG, message, null); }
	public void debug(String message, Throwable throwable) { log(DEBUG, message, throwable); }

	public void info(String message) { log(INFO, message, null); }
	public void info(String message, Throwable throwable) { log(INFO, message, throwable); }

	public void warn(String message) { log(WARN, message, null); }
	public void warn(String message, Throwable throwable) { log(WARN, message, throwable); }

	public void error(String message) { log(ERROR, message, null); }
	public void error(String message, Throwable throwable) { log(ERROR, message, throwable); }

	public void fatal(String message) { log(FATAL, message, null); }
	public void fatal(String message, Throwable throwable) { log(FATAL, message, throwable); }

	private void log(String type, String message, Throwable throwable) {
		if(!logLevels.get(type)) {
			return;
		}

		try {
			String currentDateTime = SIMPLE_DATE_FORMAT.format(new Date(System.currentTimeMillis()));
			if(null != throwable) {
				outputStream.write((String.format(LOG_FORMAT_EXCEPTION , currentDateTime, type, component, message, throwable.getMessage())).getBytes());
				throwable.printStackTrace(new PrintStream(outputStream));
			} else {
				outputStream.write((String.format(LOG_FORMAT, currentDateTime, type, component, message)).getBytes());
			}
		} catch(IOException ex) {
			System.out.println("COULD NOT LOG");
			ex.printStackTrace();
		}
	}

	public static void setOutputStream(OutputStream outputStream) { SimpleLogger.outputStream = outputStream; }

	public static void setShouldLogDebug(boolean shouldLog) { logLevels.put(DEBUG, shouldLog); }
	public static void setShouldLogInfo(boolean shouldLog) { logLevels.put(INFO, shouldLog); }
	public static void setShouldLogWarn(boolean shouldLog) { logLevels.put(WARN, shouldLog); }
	public static void setShouldLogError(boolean shouldLog) { logLevels.put(ERROR, shouldLog); }
	public static void setShouldLogFatal(boolean shouldLog) { logLevels.put(FATAL, shouldLog); }

}