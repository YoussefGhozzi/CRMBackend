package project.spring.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.entités.Message;
import project.spring.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	
	private MessageRepository messageRepository;
	
	  public long getMessagesPerDay() {
	        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
	        LocalDateTime endOfDay = startOfDay.plusDays(0);
	        List<Message> messages = messageRepository.findByTimestampBetween(startOfDay, endOfDay);
	        return messages.size();
	    }

	    public long getMessagesPerMonth() {
	        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
	        LocalDateTime endOfMonth = startOfMonth.plusMonths(0);
	        List<Message> messages = messageRepository.findByTimestampBetween(startOfMonth, endOfMonth);
	        return messages.size();
	    }
	}

