package com.shinhancard.smalltalk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shinhancard.common.util.PlainMap;
import com.shinhancard.profile.response.ProfileAggregationClient;
import com.shinhancard.profile.response.ProfileRequest;
import com.shinhancard.profile.response.ProfileResponse;
import com.shinhancard.smalltalk.dto.SmalltalkResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SmalltalkProvisionService {

	private final ProfileAggregationClient profileClient;
//	private final SmalltalkRepositofy smalltalkRepository;
	
	private final SmalltalkContentsLoggerService smalltalkContentsLogger;
	
	private static final String smallTalkTemplate = "안녕하세요 %s님! %s";
	
	public SmalltalkResponse makeSmallTalkWithProfile(String pid) {
 
		//MainConfig에서 생성한 profileClient Bean을 통한 profile-aggregator 호출
		ProfileRequest request = new ProfileRequest();
		request.setUserId(pid);
		
		ProfileResponse profile = profileClient.findPersonalProfile(request);
		
		log.info("@@@get profile from profile svc : {}",profile.getUserId());
		
		SmalltalkResponse smalltalkResponse = new SmalltalkResponse();
		smalltalkResponse.setRequestUserId(pid);
		
		String smalltalk = new String();
		String userName;
		
//		Smalltalk smallTalk = smalltalkRepository.getSmallTalk(pid);
		
		if(profile != null) {
			smalltalkResponse.setUserId(profile.getUserId());
			PlainMap personalContents = new PlainMap(profile.getContents());
			userName = personalContents.get("profile.name").toString();
			
			Map offerList = (HashMap)personalContents.get("offer");
			smalltalk = offerList.size()+"개의 offer가 있습니다.";
			
			smalltalkResponse.setMessage(String.format(smallTalkTemplate, userName, smalltalk));
			smalltalkResponse.setChatbot_url("http://chatbot-main-host/smalltalk");
		}

		log.info("@@@result for making small talk : {}",smalltalk);

		// FIXME : response에 smalltalk이 빈값인지, 고객정보가 없는건지 등의 정보를 같이 실어서 보내줘야할듯
//		if(smalltalkResponse != null)
			return smalltalkResponse;
				
	}

}
