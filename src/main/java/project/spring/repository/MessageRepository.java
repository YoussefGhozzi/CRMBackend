package project.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spring.entités.Message;
public interface MessageRepository extends JpaRepository<Message, Long>{
	  List<Message> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
