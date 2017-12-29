package com.nisum.zookeeper.config;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

public class ZookeeperPropertySourcesConfigurer extends PropertySourcesPlaceholderConfigurer
		implements EnvironmentAware, InitializingBean {
	
	private Environment environment;
	private List<PropertySource> sourceList;

	public void afterPropertiesSet() throws Exception {
		MutablePropertySources envPropSources = ((ConfigurableEnvironment)environment).getPropertySources();
        copyListToPropertySources(this.sourceList, envPropSources);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		super.setEnvironment(environment);
	}
	
	private void copyListToPropertySources(List<PropertySource> list, MutablePropertySources sources) {
        for(int i = list.size() - 1; i >= 0; i--) {
            sources.addFirst(list.get(i));
        }
    }
	
	public void setPropertySources(List<PropertySource> propertySources) {
        this.sourceList = propertySources;
        MutablePropertySources sources = new MutablePropertySources();
        copyListToPropertySources(this.sourceList, sources);        
        super.setPropertySources(sources);
    }
}
