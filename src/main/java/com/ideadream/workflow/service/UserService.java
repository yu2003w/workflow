package com.ideadream.workflow.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public List<String> getPrivilegedUsers() {
		logger.trace("getPrivilegedUsers(), retrieve privileged users");
		List<String> usrL = new ArrayList<String>();
		return usrL;
	}
	
	public List<String> getNormalUsers() {
		logger.trace("getNormalUsers(), retrieve normal users");
		
		List<String> usrL = new ArrayList<String>();
		return usrL;
	}
}
