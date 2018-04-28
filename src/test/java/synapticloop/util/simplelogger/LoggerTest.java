package synapticloop.util.simplelogger;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import synapticloop.util.simplelogger.Logger;


public class LoggerTest {

	public class TestOutputStream extends OutputStream {
		StringBuilder stringBuilder = new StringBuilder();

		@Override
		public void write(int b) throws IOException {
			stringBuilder.append(b);
		}

		@Override
		public void write(byte[] b) throws IOException {
			stringBuilder.append(new String(b));
		}

		public String getAndResetOutput() {
			try {
				return(stringBuilder.toString());
			} finally {
				stringBuilder.setLength(0);
			}
		}
	};

	@Test
	public void testGetLoggerSimpleName() {
		Logger logger = Logger.getLoggerSimpleName(Logger.class);
		assertEquals("LOGGER", logger.getComponent());

		logger = Logger.getLoggerSimpleName(String.class);
		assertEquals("STRING", logger.getComponent());

		logger = Logger.getLoggerSimpleName(IOException.class);
		assertEquals("I_O_EXCEPTION", logger.getComponent());

	}

	@Test
	public void testOutputStream() {
		TestOutputStream outputStream = new TestOutputStream();
		Logger.setOutputStream(outputStream);

		Logger one = Logger.getLogger("one");
		Logger two = Logger.getLogger("two");
		Logger three = Logger.getLogger("three");

		Logger.setShouldLogError(true);
		Logger.setShouldLogFatal(true);

		one.info("hello");
		assertLogContains(outputStream.getAndResetOutput(), "INFO", "one", "hello");

		two.warn("hello");
		assertLogContains(outputStream.getAndResetOutput(), "WARN", "two", "hello");

		three.error("hello");
		assertLogContains(outputStream.getAndResetOutput(), "ERROR", "three", "hello");

		one.debug("hello");
		assertLogContains(outputStream.getAndResetOutput(), "DEBUG", "one", "hello");

		two.fatal("hello");
		assertLogContains(outputStream.getAndResetOutput(), "FATAL", "two", "hello");

		Logger.setShouldLogDebug(false);
		one.debug("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		Logger.setShouldLogInfo(false);
		one.info("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		Logger.setShouldLogFatal(false);
		one.fatal("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		Logger.setShouldLogWarn(false);
		one.warn("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		Logger.setShouldLogError(false);
		one.error("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		Logger.setShouldLogInfo(true);
		Logger four = Logger.getLogger(LoggerTest.class);
		one.info("goodbye");
		System.out.print(outputStream.getAndResetOutput());
		four.info("ok");

		assertLogContains(outputStream.getAndResetOutput(), "INFO", LoggerTest.class.getCanonicalName(), "ok");
		assertNull(null);
	}

	public void assertLogContains(String log, String type, String component, String message) {
		System.out.print(log);
		assertTrue(log.contains(type) && log.contains(component) && log.contains(message));
	}
}
