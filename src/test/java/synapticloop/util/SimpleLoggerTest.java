package synapticloop.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;


public class SimpleLoggerTest {

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
	public void testMethodName() {
		TestOutputStream outputStream = new TestOutputStream();
		SimpleLogger.setOutputStream(outputStream);

		SimpleLogger one = SimpleLogger.getLogger("one");
		SimpleLogger two = SimpleLogger.getLogger("two");
		SimpleLogger three = SimpleLogger.getLogger("three");

		SimpleLogger.setShouldLogError(true);
		SimpleLogger.setShouldLogFatal(true);

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

		SimpleLogger.setShouldLogDebug(false);
		one.debug("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		SimpleLogger.setShouldLogInfo(false);
		one.info("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		SimpleLogger.setShouldLogFatal(false);
		one.fatal("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		SimpleLogger.setShouldLogWarn(false);
		one.warn("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		SimpleLogger.setShouldLogError(false);
		one.error("hello");
		assertEquals(outputStream.getAndResetOutput().length(), 0);

		SimpleLogger.setShouldLogInfo(true);
		SimpleLogger four = SimpleLogger.getLogger(SimpleLoggerTest.class);
		one.info("goodbye");
		System.out.print(outputStream.getAndResetOutput());
		four.info("ok");

		assertLogContains(outputStream.getAndResetOutput(), "INFO", SimpleLoggerTest.class.getCanonicalName(), "ok");
		assertNull(null);
	}

	public void assertLogContains(String log, String type, String component, String message) {
		System.out.print(log);
		assertTrue(log.contains(type) && log.contains(component) && log.contains(message));
	}
}
