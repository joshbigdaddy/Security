package com.psi1.config;

public class GlobalConfiguration {

	
	private String configurationFile;
	private String logsDirectory;
	
	
	public GlobalConfiguration(String configurationFile, String logsDirectory) {
		super();
		this.configurationFile = configurationFile;
		this.logsDirectory = logsDirectory;
	}
	
	public String getConfigurationFile() {
		return configurationFile;
	}
	public void setConfigurationFile(String configurationFile) {
		this.configurationFile = configurationFile;
	}
	public String getLogsDirectory() {
		return logsDirectory;
	}
	public void setLogsDirectory(String logsDirectory) {
		this.logsDirectory = logsDirectory;
	}
	
	
	
	
}
