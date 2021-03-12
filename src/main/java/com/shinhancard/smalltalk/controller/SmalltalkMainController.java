package com.shinhancard.smalltalk.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhancard.smalltalk.SmallTalkUrlDefine;
import com.shinhancard.smalltalk.service.SmalltalkMainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SmalltalkMainController {

	private final SmalltalkMainService provisionService;
	
//	@GetMapping("/smalltalk") 
//	public String returnSmallTalk(@RequestParam(value = "id", defaultValue = "P000000001") String pid) {

	@PostMapping(SmallTalkUrlDefine.PROFILE_AGGREGATOR_END_POINT)
	public String returnSmallTalk(@PathVariable("channelId") String channelId, @PathVariable("userId") String userId) {
		log.info("# input pid in ProvsionMainCtrl: {} ", userId);
		
		StringBuffer response = new StringBuffer();
		String smalltalk = provisionService.makeSmallTalkWithProfile(userId);
		
		response.append(smalltalk);
		log.info("##output smallTalk in ProvsionMainCtrl: {} ", response); 
		
		// FIXME : 외부에 제공할 response 형식 결정해서 SmallTalkResponse로 전달
		return response.toString();
	}

}
