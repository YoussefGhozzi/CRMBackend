package project.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import project.spring.service.MessageService;

@Component
public class ScheduledTasks {
    private static long dailyMessageCount;
    private static long monthlyMessageCount;

    @Autowired
    private MessageService messageService;

    @Scheduled(cron = "0 0 0 * * ?") // Exécuter à minuit tous les jours
    public void calculateDailyMessages() {
        dailyMessageCount = messageService.getMessagesPerDay();
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Exécuter à minuit le premier jour de chaque mois
    public void calculateMonthlyMessages() {
        monthlyMessageCount = messageService.getMessagesPerMonth();
    }

    public static long getDailyMessageCount() {
        return dailyMessageCount;
    }

    public static long getMonthlyMessageCount() {
        return monthlyMessageCount;
    }
}
