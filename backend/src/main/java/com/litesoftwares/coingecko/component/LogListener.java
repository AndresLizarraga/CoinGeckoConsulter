package com.litesoftwares.coingecko.component;

public interface LogListener {

	public void onInfo(String mensaje);
	
	public void onDebug(String mensaje);
	
	public void onFatal(String mensaje);
	
	public void onWarn(String mensaje);
	
}
