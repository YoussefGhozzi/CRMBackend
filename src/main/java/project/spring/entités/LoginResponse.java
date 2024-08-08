package project.spring.entités;

public class LoginResponse {
	  private Long id;
	    private String role;
	    private String nom;
	    private String prenom;
	    private String email;
	    private String Adresse;
	    private Integer Age;
	    
	    public LoginResponse(Long id, String nom, String prenom, String role,String email,String Adresse, Integer Age) {
	        this.id = id;
	        this.nom=nom;
	        this.prenom=prenom;
	        this.role = role;
	        this.email=email;
	        this.Adresse=Adresse;
	        this.Age=Age;
	    }

	

		// Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }



		public String getNom() {
			return nom;
		}



		public void setNom(String nom) {
			this.nom = nom;
		}



		public String getPrenom() {
			return prenom;
		}



		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getAdresse() {
			return Adresse;
		}



		public void setAdresse(String adresse) {
			Adresse = adresse;
		}



		public Integer getAge() {
			return Age;
		}



		public void setAge(Integer age) {
			Age = age;
		}
		
	    
}
