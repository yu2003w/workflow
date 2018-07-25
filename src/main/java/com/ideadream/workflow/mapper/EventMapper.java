package com.ideadream.workflow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {

	public List<String> getTaskId();
	
}
