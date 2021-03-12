package com.shinhancard.smalltalk.service;

import org.springframework.stereotype.Service;

import com.shinhancard.profile.ProfileAggregationClient;
import com.shinhancard.profile.ProfileIF;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SmalltalkMainService {

	private final ProfileAggregationClient profileClient;
	
	private final SmalltalkContentsLoggerService smalltalkContentsLogger;
	
	private static final String smallTalkTemplate = "안녕하세요 %s님! %s";
	
	public String makeSmallTalkWithProfile(String pid) {
 
		//MainConfig에서 생성한 profileClient Bean을 통한 profile-aggregator 호출
		ProfileIF profile = profileClient.findPersonalProfile(pid);
		
		log.info("@@@get profile from profile svc : {}",profile);
		
		String smalltalk = new String();
		String response=null, userId, userName="default";
		
		if(profile != null) {
			userId = profile.getUserId();
			
			if("P000000002".equals(userId)) { //홍길
				smalltalk = "짚신 사가세요.";
				userName = "홍길동";
			}else {
				smalltalk = "기본 스몰톡입니다.";
				userName = userId;
			}
			
			response = String.format(smallTalkTemplate, userName, smalltalk);
		}

		log.info("@@@result for making small talk : {}",smalltalk);

		// FIXME : response에 smalltalk이 빈값인지, 고객정보가 없는건지 등의 정보를 같이 실어서 보내줘야할듯
		if(response != null) {
			return response;
		}else if(profile == null) {
			return "fail1 : profile object is not exist";		
		}else {
			return "fail2 : personal smalltalk is not exist";
		}
		
	}

}
