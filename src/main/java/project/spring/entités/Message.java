package project.spring.entités;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String content;
	    private String sender;
	    private LocalDateTime timestamp;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
		@Override
		public String toString() {
			return "Message [id=" + id + ", content=" + content + ", sender=" + sender + ", timestamp=" + timestamp
					+ ", getContent()=" + getContent() + ", getSender()=" + getSender() + ", getTimestamp()="
					+ getTimestamp() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
					+ super.toString() + "]";
		}

	    
	    
	}

