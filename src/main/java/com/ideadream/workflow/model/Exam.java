package com.ideadream.workflow.model;

import java.io.Serializable;

public class Exam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7845719337557453589L;

	private String taskId;
	private Long examId;
	private String taskName;
	private String examName;

	public Exam(String taskId, String taskName) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
	}

	public Exam(String taskId, Long examId, String taskName, String examName) {
		super();
		this.taskId = taskId;
		this.examId = examId;
		this.taskName = taskName;
		this.examName = examName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	@Override
	public String toString() {
		return "Exam [taskId=" + taskId + ", examId=" + examId + ", taskName=" + taskName + ", examName=" + examName
				+ "]";
	}

}
