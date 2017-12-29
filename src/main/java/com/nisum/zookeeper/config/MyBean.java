package com.nisum.zookeeper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
	
//	public MyBean(String legacy, String cloud, String zookeeper) {
//		this.legacy = legacy;
//		this.cloud = cloud;
//		this.zookeeper = zookeeper;
//	}
	
	@Value("${node}")
	private String legacy;
	@Value("${node}")
	private String cloud;
	@Value("${node}")
	private String zookeeper;
	
	public String getLegacy() {
		return legacy;
	}
	public String getCloud() {
		return cloud;
	}
	public String getZookeeper() {
		return zookeeper;
	}
}