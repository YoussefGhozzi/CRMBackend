package project.spring.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import project.spring.entités.User;
import project.spring.repository.UserRepository;
import project.spring.repository.ProviderRepository;
import project.spring.entités.LoginClient;
import project.spring.entités.Employé;
import project.spring.util.TokenUtil;
import project.spring.service.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private EmailService emailService;

    @Value("${app.url.local}")
    private String localUrl;

    @Value("${app.url.prod}")
    private String prodUrl;

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    public List<String> register(User newUser, BindingResult result) {
        // Vérifier si l'email est déjà utilisé par un utilisateur
        List<String> errors = new ArrayList<>();
        Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
        if (potentialUser.isPresent()) {
            errors.add("Email déjà utilisé");
            return errors;
        }

        // Vérifier si l'email est déjà utilisé par un fournisseur
        Optional<Employé> potentialProvider = providerRepository.findByEmail(newUser.getEmail());
        if (potentialProvider.isPresent()) {
            errors.add("Email déjà utilisé");
            return errors;
        }

        // Vérifier si les mots de passe correspondent
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            errors.add("Les mots de passe ne correspondent pas");
            return errors;
        }

        // Hasher le mot de passe
        String hashedPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPW);

        // Générer et envoyer le token de confirmation si nécessaire
        if (!newUser.isActive()) {
            String token = TokenUtil.generateToken();
            newUser.setConfirmationToken(token);
            newUser.setActive(false);
            
            // Envoyer l'email de confirmation
            String confirmationUrl = localUrl + "/confirm-account?token=" + token;
            String message = "Veuillez cliquer sur le lien pour confirmer votre adresse e-mail: " + confirmationUrl;
            emailService.sendSimpleEmail(newUser.getEmail(), "Confirmation Email", message);
        }

        // Enregistrer l'utilisateur
        userRepository.save(newUser);
        return errors;
    }

    public User login(LoginClient newLoginObject, BindingResult result) {
        Optional<User> potentialLogin = userRepository.findByEmail(newLoginObject.getEmail());
        
        if (!potentialLogin.isPresent()) {
            result.rejectValue("email", "loginError", "Email ou mot de passe incorrect! Veuillez le vérifier.");
        } else {
            User actualUser = potentialLogin.get();
            if (!BCrypt.checkpw(newLoginObject.getPassword(), actualUser.getPassword())) {
                result.rejectValue("password", "loginError", "Email ou mot de passe incorrect! Veuillez le vérifier.");
            }
            if (result.hasErrors()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ou mot de passe incorrect! Veuillez le vérifier.");
            } else {
                return actualUser;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ou mot de passe incorrect! Veuillez le vérifier.");
    }


    public User loginWithGoogle(String email) {
        Optional<User> potentialUser = userRepository.findByEmail(email);
        if (potentialUser.isPresent()) {
            return potentialUser.get();
        }
        return null;
    }

    public User updateUserDetails(User user, Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setPrenom(user.getPrenom());
            updatedUser.setNom(user.getNom());
            updatedUser.setPassword(user.getPassword());
            return userRepository.save(updatedUser);
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
