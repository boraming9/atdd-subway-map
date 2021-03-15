package com.shinhancard.smalltalk.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhancard.smalltalk.SmallTalkUrlDefine;
import com.shinhancard.smalltalk.dto.SmalltalkResponse;
import com.shinhancard.smalltalk.service.SmalltalkProvisionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SmalltalkProvisionController {

	private final SmalltalkProvisionService provisionService;
	
//	@GetMapping("/smalltalk") 
//	public String returnSmallTalk(@RequestParam(value = "id", defaultValue = "P000000001") String pid) {

	@PostMapping(SmallTalkUrlDefine.SMALLTALK_PROVISION_ENDPOINT)
	public SmalltalkResponse returnSmallTalk(@PathVariable("channelId") String channelId, @PathVariable("userId") String userId) {
		log.info("# input pid in ProvsionMainCtrl: {} ", userId);
		
		SmalltalkResponse smalltalk = provisionService.makeSmallTalkWithProfile(userId);
		
		log.info("##output smallTalk in ProvsionCtrl: {} ", smalltalk.getUserId()); 
		
		// FIXME : 외부에 제공할 response 형식 결정해서 SmallTalkResponse로 전달
		return smalltalk;
	}

}
