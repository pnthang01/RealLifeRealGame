package com.rlrg.webserver.frontend.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rlrg.utillities.exception.SessionManagerException;

@Service
public class SessionService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SessionService.class);
	
	private static Map<String, String> tokenSessions;
	
	public void addTokenSession(String sessionId, String token) throws SessionManagerException{
		if(null == sessionId || null == token){
			throw new NullPointerException("Either session or token are null.");
		}
		if(tokenSessions.containsKey(sessionId)){
			LOG.debug("This sessionId {} already exists in Session Manager", sessionId);
			throw new SessionManagerException();
		}
		tokenSessions.put(sessionId, token);
	}
	
	public boolean isTokenExist(String token){
		if(null == token){
			throw new NullPointerException("Token is null.");
		}
		return tokenSessions.containsKey(token);
	}
	
	public String getToken(String sessionId){
		if(null == sessionId){
			throw new NullPointerException("Session is null");
		}
		return tokenSessions.get(sessionId);
	}

}
