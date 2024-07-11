package project.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EmailService1 {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

    public EmailService1(RestTemplate restTemplate,
                         @Value("${unipile.api.url}") String apiUrl,
                         @Value("${unipile.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public String sendEmail(String displayName, String identifier, String body, String accountId, MultipartFile[] attachments) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("account_id", accountId);
        formData.add("display_name", displayName);
        formData.add("identifier", identifier);
        formData.add("body", body);

        // Ajout des pièces jointes
        for (MultipartFile attachment : attachments) {
            try {
                formData.add("attachments", new org.springframework.core.io.ByteArrayResource(attachment.getBytes()) {
                    @Override
                    public String getFilename() {
                        return attachment.getOriginalFilename();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to read attachment file", e);
            }
        }

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
        System.out.println("Email sent, response: " + response.getBody());
		return accountId;
    }
}
