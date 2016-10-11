package com.luxoft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan("com.luxoft")
@EnableTransactionManagement
public class AppConfig extends WsConfigurerAdapter {

	@Bean(name = "todo")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema todoSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("TodoPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://luxoft.com/soap");
		wsdl11Definition.setSchema(todoSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema todoSchema() {
		return new SimpleXsdSchema(new ClassPathResource("todo.xsd"));
	}
}
