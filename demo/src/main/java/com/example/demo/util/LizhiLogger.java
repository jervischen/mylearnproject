package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LizhiLogger {

	private static final Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

	public static void info(String format, Object... arg) {
		logger.info(format, arg);
	}

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void error(String msg) {
		logger.error(msg);
	}

	public static void error(String format, Object... arg) {
		logger.error(format, arg);
	}

	public static void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public static void error(String msg, Object arg, Throwable t) {
		logger.error(msg, arg, t);
	}
}
