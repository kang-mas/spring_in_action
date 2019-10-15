package tacos.web;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/nmis").setViewName("NmisFront");
	}
	
	@Bean
	ServletRegistrationBean<WebdavServlet> h2ServletRegistrationBean() {
		ServletRegistrationBean<WebdavServlet> registrationBean= new ServletRegistrationBean<WebdavServlet>(new  WebdavServlet());
		registrationBean.addUrlMappings("/console/*");
		return  registrationBean;
	}
}
