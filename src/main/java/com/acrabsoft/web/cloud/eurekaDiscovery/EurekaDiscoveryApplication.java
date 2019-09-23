package com.acrabsoft.web.cloud.eurekaDiscovery;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EurekaDiscoveryApplication {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@RequestMapping("/api/test")
    public String home() {
		System.out.println(request.getHeader("x-b3-traceid"));
		response.setHeader("xxx-1", request.getHeader("x-b3-traceid"));
        return "Hello world";
    }
	 
	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryApplication.class, args);
	}
	private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            System.out.println(key +" : "+value);
            map.put(key, value);
        }
        return map;
    }


}
