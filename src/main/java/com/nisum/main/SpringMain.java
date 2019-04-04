package com.nisum.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import com.nisum.zookeeper.config.MyBean;
import com.nisum.zookeeper.config.ZookeeperPropertySource;

public class SpringMain {

	public static void main(String[] args) {
		ZookeeperXmlConfig app = new ZookeeperXmlConfig();
		app.run();
	}
}

class ZookeeperXmlConfig {
	public void run() {
		ApplicationContext context = createAppContext();

		MyBean mybean = context.getBean(MyBean.class);

		System.out.println(mybean.getLegacy());
		System.out.println(mybean.getCloud());
		System.out.println(mybean.getZookeeper());
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 mybean = context.getBean(MyBean.class);

		System.out.println(mybean.getLegacy());
		System.out.println(mybean.getCloud());
		System.out.println(mybean.getZookeeper());
	}
	hisdfgsdfg

	private ApplicationContext createAppContext() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:/app-context.xml" }, false);

		ConfigurableEnvironment env = context.getEnvironment();
		env.getPropertySources().addFirst(getPropertySource());
		context.refresh();

		printSources(env);
		System.out.println("---- System properties -----");
		return context;
	}

	private static void printSources(ConfigurableEnvironment env) {
		System.out.println("---- property sources ----");
		for (PropertySource<?> propertySource : env.getPropertySources()) {
			System.out.println("name =  " + propertySource.getName() + "\nsource = "
					+ propertySource.getSource().getClass() + "\n");
			//System.err.println(propertySource.toString());
			if (propertySource.containsProperty("node")) {
				System.err.println(propertySource.getProperty("node"));
			}
		}
	}

	private PropertySource getPropertySource() {
		ClassPathXmlApplicationContext propertySourceContext = new ClassPathXmlApplicationContext(
				new String[] { "classpath:/properties-source-context.xml" });

		return propertySourceContext.getBean(ZookeeperPropertySource.class);
	}
}
