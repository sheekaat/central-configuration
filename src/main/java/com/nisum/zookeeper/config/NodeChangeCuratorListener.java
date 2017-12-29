package com.nisum.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

public class NodeChangeCuratorListener implements ApplicationContextAware, CuratorListener, Watcher {

	AbstractApplicationContext ctx;

	public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
		if (event.getType() == CuratorEventType.WATCHED) {
			ctx.refresh();
		}
	}

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = (AbstractApplicationContext) ctx;
	}

	public void process(WatchedEvent event) {
		switch (event.getType()) {
		case NodeChildrenChanged:
			break;
		case NodeDataChanged:
			ctx.refresh();
			break;
		case NodeDeleted:
			break;
		default:
			break;
		}

	}

}
