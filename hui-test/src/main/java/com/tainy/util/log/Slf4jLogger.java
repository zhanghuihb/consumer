package com.tainy.util.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * The Slf4jLogger implementation of Logger.
 */
public class Slf4jLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	Slf4jLogger(org.slf4j.Logger impl) {
		_impl = impl;
	}

	@Override
	public String getName() {
		return _impl.getName();
	}

	@Override
	public void trace(String message) {
		_impl.trace(Request.getId() + "-" + message);
	}

	@Override
	public void trace(String format, Object... args) {
		_impl.trace(Request.getId() + "-" + format, args);
	}

	@Override
	public boolean isTraceEnabled() {
		return _impl.isTraceEnabled();
	}

	@Override
	public void debug(String message) {
		_impl.debug(Request.getId() + "-" + message);
	}

	@Override
	public void debug(String format, Object... args) {
		_impl.debug(Request.getId() + "-" + format, args);
	}

	@Override
	public boolean isDebugEnabled() {
		return _impl.isDebugEnabled();
	}

	@Override
	public void info(String message) {
		_impl.info(Request.getId() + "-" + message);
	}

	@Override
	public void info(String format, Object... args) {
		_impl.info(Request.getId() + "-" + format, args);
	}

	@Override
	public boolean isInfoEnabled() {
		return _impl.isInfoEnabled();
	}

	@Override
	public void warn(String message) {
		_impl.warn(Request.getId() + "-" + message);
		try {
			// //Cat.logEvent("Exception", Request.getId(), Message.SUCCESS,
			// Request.getId() +"-"+ message);
		} catch (Exception e) {
		}
	}

	@Override
	public void warn(String format, Object... args) {
		_impl.warn(Request.getId() + "-" + format, args);
		try {
			// //Cat.logEvent("Exception", Request.getId(), Message.SUCCESS,
			// Request.getId() +"-"+ format);
		} catch (Exception e) {
		}
	}

	@Override
	public boolean isWarnEnabled() {
		return _impl.isWarnEnabled();
	}

	@Override
	public void error(String message) {
		_impl.error(Request.getId() + "-" + message);
		try {
			// Cat.logEvent("Exception", Request.getId(), Message.SUCCESS,
			// Request.getId() +"-"+ message);
		} catch (Exception e) {
		}
	}

	@Override
	public void error(String format, Object... args) {
		_impl.error(Request.getId() + "-" + format, args);
		try {
			// Cat.logEvent("Exception", Request.getId(), Message.SUCCESS,
			// Request.getId() +"-"+ format);
		} catch (Exception e) {
		}
	}

	@Override
	public void error(Exception ex) {
		_impl.error(Request.getId(), ex);
		try {
			// Cat.logError(ex);
		} catch (Exception e) {
		}
	}

	@Override
	public void error(String message, Exception ex) {
		_impl.error(Request.getId() + "-" + message, ex);
		try {
			// Cat.logError(message,ex);
		} catch (Exception e) {
		}
	}

	public static String estacktack2Str(Exception ex) {
		PrintStream ps = null;
		try {
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ps = new PrintStream(bao);
			ex.printStackTrace(ps);
			return bao.toString("utf-8");
		} catch (UnsupportedEncodingException e) {
			return e.getMessage();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	@Override
	public boolean isErrorEnabled() {
		return _impl.isErrorEnabled();
	}

	//
	// private fields
	//

	private org.slf4j.Logger _impl;

}
