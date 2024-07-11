package project.spring.entités;

public class MessageRequest {
    private String chatId;
    private String text;

    // Constructeurs
    public MessageRequest() {}

    public MessageRequest(String chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    // Getters et Setters
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
