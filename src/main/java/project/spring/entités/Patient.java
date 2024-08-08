package project.spring.entités;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients", uniqueConstraints = {
        @UniqueConstraint(columnNames = "telephone"),
        @UniqueConstraint(columnNames = "facebook_link"),
        @UniqueConstraint(columnNames = "whatsapp_num"),
        @UniqueConstraint(columnNames = "num_dossier"),
        @UniqueConstraint(columnNames = "adresse_Mail")
})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private int age;
    private String telephone;
    private String adresse;
    private String nationalite;
    private String canalTraitement;
    private String source;
    
    
    private Date dateTraitement;  
    
    
   
    private Date dateRDV;
    
    
    
    private String facebookLink;
    private String whatsappNum;
    

    private String numDossier;

    private String adresseMail;
    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getCanalTraitement() {
		return canalTraitement;
	}

	public void setCanalTraitement(String canalTraitement) {
		this.canalTraitement = canalTraitement;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public Date getDateRDV() {
		return dateRDV;
	}

	public void setDateRDV(Date dateRDV) {
		this.dateRDV = dateRDV;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getWhatsappNum() {
		return whatsappNum;
	}

	public void setWhatsappNum(String whatsappNum) {
		this.whatsappNum = whatsappNum;
	}

	public Set<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(Set<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	
    
    
    
    @ManyToMany(mappedBy = "patients")
    private Set<Entreprise> entreprises = new HashSet<>();

    public Patient() {}

    public Patient(String nom, String prenom, String tel, String nationalite, String canalTraitement, String source,
                   Date dateTraitement, Date dateRDV, String facebookLink, String whatsappNum, String numDossier, String adresseMail) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = tel;
        this.nationalite = nationalite;
        this.canalTraitement = canalTraitement;
        this.source = source;
        this.dateTraitement = dateTraitement;
        this.dateRDV = dateRDV;
        this.facebookLink = facebookLink;
        this.whatsappNum = whatsappNum;
        this.numDossier = numDossier;
        this.adresseMail = adresseMail;
    }

    // Getters and Setters
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

 

    public Set<Entreprise> getCabinets() {
        return entreprises;
    }

    public void setCabinets(Set<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}
    
}
