package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SentenceServiceImpl implements SentenceService{


	@Autowired VerbFeignClient verbClient;
	
	@HystrixCommand(fallbackMethod="getFallbackVerb")
	  public String getVerb() {
		return verbClient.getWord();
	  }
	  
	  public String getFallbackVerb(){
		  return "HystrixHappen";
	  }
}
