package project.spring.entités;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
public class LoginClient {
	 @NotEmpty(message = "L'email est requis")
	    @Email(message = "Vous devez entrer un email valide")
	    private String email;

	    @NotEmpty(message = "Le mot de passe est requis")
	    @Size(min = 8, max = 128, message = "Le mot de passe doit avoir entre 8 et 128 caractères")
	    private String password;

	    public LoginClient() {
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
}
