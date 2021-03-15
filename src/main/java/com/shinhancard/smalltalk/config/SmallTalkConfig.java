package com.shinhancard.smalltalk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.shinhancard.profile.response.ProfileAggregationClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SmallTalkConfig {

	/* profile-aggregation-service host */
	String host = "localhost:18080";
	
	// TODO : 이 호스트명에 맞는 ip는 따로 설정파일이 있나?
//	String host = "chatbot-api-gateway-service";
	
	@Autowired
	RestTemplate restTemplate;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	ProfileAggregationClient profileClient() {
		
		ProfileAggregationClient profileClient = ProfileAggregationClient.builder().host(host).restTemplate(restTemplate).build();
		log.info("~~~0.init. profileClient Builder info {}",profileClient);
		
		return profileClient;
	}

}
