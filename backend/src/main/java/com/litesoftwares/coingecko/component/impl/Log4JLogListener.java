package com.litesoftwares.coingecko.component.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.litesoftwares.coingecko.component.LogListener;

@Component
public class Log4JLogListener implements LogListener {

	private static Logger logger = Logger.getLogger(Log4JLogListener.class);
	
	@Override
	public void onInfo(String mensajeNivelInfo) {
		logger.info(getLogHeader() + mensajeNivelInfo);
	}

	@Override
	public void onDebug(String mensaje) {
		logger.debug(getLogHeader() + mensaje);
	}

	@Override
	public void onFatal(String mensaje) {
		logger.fatal(getLogHeader() + mensaje);
	}

	@Override
	public void onWarn(String message) {
		logger.warn(getLogHeader() + message);
	}

	/**
	 * Obtiene la clase y el método que llama al log mirando el Stacktrace
	 * @return
	 */
	private String getLogHeader() {
		String callerClass = "Unknown";
		String method = "Unkown method";
		
		for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
			if (!(ste.getClassName().equals(Thread.class.getName()) || ste.getClassName().equals(this.getClass().getName()))) {
				callerClass = ste.getClassName();
				method = ste.getMethodName();
				break;
			}
		}
		
		String parts[] = callerClass.split("[.]");
		
		String shortClassName = parts[parts.length - 1];
		
		return "[" + shortClassName + "] => " + method + " - ";
	}
	
}
