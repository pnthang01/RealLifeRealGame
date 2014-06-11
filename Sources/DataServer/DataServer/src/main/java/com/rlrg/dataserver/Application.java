package com.rlrg.dataserver;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.naming.resources.VirtualDirContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.rlrg.dataserver.utils.base.domain.UserToken;
import com.rlrg.utillities.json.JsonExporter;

@ComponentScan
@EnableAutoConfiguration
@PropertySources({
	@PropertySource("classpath:application.properties")
})
public class Application {
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
	@Bean
	public PathMatchingResourcePatternResolver resourcePatternResolver() {
		return new PathMatchingResourcePatternResolver();
	}

	@Bean
	public JsonExporter initJsonExporter(){
		return new JsonExporter();
	}
	
	@Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.addContextLifecycleListeners(new LifecycleListener() {
			
			@Override
			public void lifecycleEvent(LifecycleEvent event) {
				// TODO: Use Logger instead of System.out.println
				StandardContext ctx = (StandardContext)event.getSource();
//				System.out.println("ContextLifeCycleLister.lifecycleEvent: " + event.getType());

				if (Lifecycle.AFTER_START_EVENT.equals(event.getType())) {
					if (event.getSource() instanceof StandardContext) {
						try {
							PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
							Resource[] res = resourcePatternResolver.getResources("classpath*:/META-INF");
							
							for (Resource r : res) {
								String path = r.getFile().getAbsolutePath();
								path = path.substring(0, path.indexOf("\\META-INF"));
								LOG.info("<< Add to ResourceDirContext: {}", path);
								VirtualDirContext dirContext = new VirtualDirContext();
								dirContext.setDocBase(path);
								ctx.addResourcesDirContext(dirContext);
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						LOG.error("<< Event source not instance of StandardContext");
					}
					
				}			
				
			}
		});
		return factory;
    }
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
