package com.ideadream.workflow.controller;

import java.util.ArrayList;
import java.util.List;

import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideadream.workflow.model.Exam;
import com.ideadream.workflow.service.PreExamService;

@RestController
public class ExamController {

	private static final Logger logger = LoggerFactory.getLogger(ExamController.class);
	
	@Autowired
	private PreExamService preExamS;
	
	@RequestMapping(value="/preexam", method=RequestMethod.POST)
	public void startPreExam() {
		logger.debug("startPreExam(), prepare examination");
		preExamS.startProcess();
	}
	
	@RequestMapping(value="/exams", method = RequestMethod.GET)
	public List<Exam> getExams(@RequestParam String assignee) {
		List<Task> tasks = preExamS.getTasksByOwner(assignee);
		
		List<Exam> examT = new ArrayList<Exam>();
		for (Task t: tasks) {
			examT.add(new Exam(t.getId(), t.getName()));
		}
		logger.trace("getExams(), " + examT.toString());
		return examT;
	}
}
