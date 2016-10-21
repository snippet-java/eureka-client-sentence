package com.example;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class EurekaClientController {
	
//	@Autowired DiscoveryClient client;
	@Autowired LoadBalancerClient lbclient;
	@Autowired SentenceService sentenceService;
	
	@RequestMapping("/")
	  public @ResponseBody String getSentence() {
	    return 
	      getSubject("SUBJECT-TEST") + " "
	      + sentenceService.getVerb();
	  }

	  public String getSubject(String service) {
//	    List<ServiceInstance> list = client.getInstances(service);
		ServiceInstance serviceInstance = lbclient.choose(service);
//	    if (list != null && list.size() > 0 ) {
//	      URI uri = list.get(0).getUri();
		URI uri = serviceInstance.getUri();
	  if (uri !=null ) {
	    return (new RestTemplate()).getForObject(uri,String.class);
	  }
//	    }
	    return null;
	  }
	  
//	  public String getVerb(String service) {
//		  
//	  }
	  
}
