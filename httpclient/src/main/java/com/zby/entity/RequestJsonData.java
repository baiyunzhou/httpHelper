package com.zby.entity;

import java.util.List;

public class RequestJsonData {
	private String basehost;
	private Integer basePort;
	private String basePath;
	private String defaultMethod;
	private List<Request> requests;

	public String getBasehost() {
		return basehost;
	}

	public void setBasehost(String basehost) {
		this.basehost = basehost;
	}

	public Integer getBasePort() {
		return basePort;
	}

	public void setBasePort(Integer basePort) {
		this.basePort = basePort;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getDefaultMethod() {
		return defaultMethod;
	}

	public void setDefaultMethod(String defaultMethod) {
		this.defaultMethod = defaultMethod;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "RequestJsonData [basehost=" + basehost + ", basePort=" + basePort + ", basePath=" + basePath + ", defaultMethod="
				+ defaultMethod + ", requests=" + requests + "]";
	}

}
