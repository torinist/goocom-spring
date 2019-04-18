package com.torinist.goocom.util.exception;

public class LogicException extends Exception {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 8373963956987336661L;

	public LogicException(String message) {
		super(message);
	}

	public LogicException(Throwable cause) {
		super(cause);
	}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
}
