package com.example;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SentenceService {


	@Autowired LoadBalancerClient lbclient;
	
	@HystrixCommand(fallbackMethod="getFallbackVerb")
	  public String getVerb() {
		  System.out.println("cheok ome into getVerb method");
//	    List<ServiceInstance> list = client.getInstances(service);
		ServiceInstance serviceInstance = lbclient.choose("VERB");
//	    if (list != null && list.size() > 0 ) {
//	      URI uri = list.get(0).getUri();
		URI uri = serviceInstance.getUri();
		System.out.println("cheok verb uri: " + uri.toString());
	    return (new RestTemplate()).getForObject(uri,String.class);
	  }
	  
	  public String getFallbackVerb(){
		  System.out.println("cheok started hystrix process");
		  return "HystrixHappen";
	  }
}
