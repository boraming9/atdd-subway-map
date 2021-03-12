package com.shinhancard.smalltalk.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Service
public class SmalltalkContentsLoggerService {

	ObjectMapper mapper = new ObjectMapper();

	private static Logger log = LoggerFactory.getLogger(SmalltalkContentsLoggerService.class);
	private static final Logger chatlog = LoggerFactory.getLogger("personal-contents-message");
 
	void write(ChatbotLog chatbotLog) {
		String result = "";
		try {
			result = mapper.writeValueAsString(chatbotLog.map);
		} catch (JsonProcessingException e) {
			log.error("{}", e);
		}

		chatlog.info(result);
	}

	@Getter
	@Setter
	@ToString
	public class ChatbotLog {
		private Date startDate;
		private Date endDate;

		private Map<String, Object> map = new HashMap<String, Object>();

		void start() {
			// 시작시간
			startDate = new Date();
			add("startDate", dateToFormatString(startDate));
		}

		void end() {
			// 종료시간
			endDate = new Date();

			// 소요시간 계산
			long diff = endDate.getTime() - startDate.getTime();

			add("duration", diff);
			add("endDate", dateToFormatString(endDate));
		}

		void add(String key, Object value) {
			map.put(key, value);
		}
		
		void addAll(Map<String, Object> value) {
			map.putAll(value); 
		}
	}

	private String dateToFormatString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);

		return dTime;

	}
}
