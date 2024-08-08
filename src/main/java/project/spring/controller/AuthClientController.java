package project.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.entités.User;
import project.spring.entités.LoginClient;
import project.spring.service.ClientService;
import project.spring.entités.LoginResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User newClient, BindingResult result, HttpSession session) {
        // Appel au service pour enregistrer le client
        List<String> errors = clientService.register(newClient, result);

        // Si des erreurs de validation existent, les renvoyer au client
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        // Si le client est enregistré avec succès, retourner une réponse 201 CREATED
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginClient newLogin, BindingResult result, HttpSession session) {
        User client = clientService.login(newLogin, result);
        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors().stream()
                                               .map(objectError -> objectError.getDefaultMessage())
                                               .collect(Collectors.toList());
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("client_id", client.getId());
        LoginResponse loginResponse = new LoginResponse(client.getId(), client.getNom(),client.getPrenom(),client.getRole(),client.getEmail(),client.getAdresse(),client.getAge());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/loginWithGoogle")
    public ResponseEntity<?> loginWithGoogle(@RequestParam String email, HttpSession session) {
        User client = clientService.loginWithGoogle(email);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        session.setAttribute("client_id", client.getId());
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    
}
