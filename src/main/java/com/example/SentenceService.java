package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SentenceService implements SentenceServiceInterface{


//	@Autowired LoadBalancerClient lbclient;
	@Autowired VerbClient verbClient;
	
	@HystrixCommand(fallbackMethod="getFallbackVerb")
	  public String getVerb() {
		  System.out.println("cheok come into getVerb method");
//	    List<ServiceInstance> list = client.getInstances(service);
//		ServiceInstance serviceInstance = lbclient.choose("VERB");
//	    if (list != null && list.size() > 0 ) {
//	      URI uri = list.get(0).getUri();
//		URI uri = serviceInstance.getUri();
//		System.out.println("cheok verb uri: " + uri.toString());
//	    return (new RestTemplate()).getForObject(uri,String.class);
		return verbClient.getWord();
	  }
	  
	  public String getFallbackVerb(){
		  System.out.println("cheok started hystrix process");
		  return "HystrixHappen";
	  }
}
