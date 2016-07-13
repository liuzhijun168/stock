package com.lzj.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lzj.DataTools;
import com.lzj.service.ScheduleTaskService;

/** 
  * @author  liuzhijun 
  * @date 创建时间：2016年4月11日 上午10:40:17 
*/
@Component("scheduleTaskService")
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

	public static final Logger logger = LoggerFactory.getLogger(ScheduleTaskServiceImpl.class);

	public void execute() {
		System.out.println("ScheduleTaskServiceImpl.execute()");
		DataTools.loadLastestData(0);
	}
	
	

}
