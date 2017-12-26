package com.test.entity;

public class Log {
	private String log_object;
	private String log_content;
	public String getLog_object() {
		return log_object;
	}
	public void setLog_object(String log_object) {
		this.log_object = log_object;
	}
	public String getLog_content() {
		return log_content;
	}
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
	@Override
	public String toString() {
		return "Log [log_object=" + log_object + ", log_content=" + log_content + "]";
	}
	
}
