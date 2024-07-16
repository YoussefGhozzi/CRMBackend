package project.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.service.MessageService;
import project.spring.util.ScheduledTasks;


@RestController
@RequestMapping("/api/messages")
public class MessageController {
	  @Autowired
	    private MessageService messageService;

	    @GetMapping("/daily-count")
	    public long getDailyMessageCount() {
	        return messageService.getMessagesPerDay();
	    }

	    @GetMapping("/monthly-count")
	    public long getMonthlyMessageCount() {
	        return messageService.getMessagesPerMonth();
	    }

	    @GetMapping("/daily-static-count")
	    public long getStaticDailyMessageCount() {
	        return ScheduledTasks.getDailyMessageCount();
	    }

	    @GetMapping("/monthly-static-count")
	    public long getStaticMonthlyMessageCount() {
	        return ScheduledTasks.getMonthlyMessageCount();
	    }
}
