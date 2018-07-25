package com.ideadream.workflow.service;


import java.util.List;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideadream.workflow.mapper.EventMapper;

@Service
public class PreExamService {

	private static final Logger logger = LoggerFactory.getLogger(PreExamService.class);
	
	@Autowired
	private RuntimeService runS;
	
	@Autowired
	private TaskService taskS;
	
	@Autowired
	private EventMapper evtM;
	
	@Transactional
	public void startProcess() {
		logger.debug("startProcess(), start PreExam process");
		logger.debug("startProcess(), event logs, task ids->" + evtM.getTaskId());
		runS.startProcessInstanceByKey("PreExam");
	}
	
	@Transactional
	public List<Task> getTasksByOwner(String assignee) {
		return taskS.createTaskQuery().taskAssignee(assignee).list();
	}
	
}
