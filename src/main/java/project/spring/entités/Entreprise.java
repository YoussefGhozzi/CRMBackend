package project.spring.entités;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   

	private String nom_Entreprise;
    private String type;
    
    @OneToOne
    @JoinColumn(name="medecin_fk")
    private Medecin medecin;
    
    @OneToOne
    @JoinColumn(name="secretaire_fk")
    private Secretaire secretaire;
//ki tbda one to one thotha min chira berka y3ni min entitée wehda berka defineeha w khw adi khter one to one eli bch ysir lhney  howa nefsou 8adi  
    

    @OneToMany(mappedBy = "entreprise") // "entreprise" refers to the field in Employé class
    private Set<Employé> employés = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "Entreprise_patient",
        joinColumns = @JoinColumn(name = "Entrepise_id"),
        inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<Patient> patients = new HashSet<>();
// ki ybda andek many tto many dima lezmk thothom fi tableau ekher les primary key de chaque tableau w tzid tmchi li tableau lkher eli howa patient fi cas hethya w definelou many to many
    
//
    // Constructeur par défaut
    public Entreprise() {}

    // Getters and Setters
    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Secretaire getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(Secretaire secretaire) {
        this.secretaire = secretaire;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getNom_Entreprise() {
		return nom_Entreprise;
	}

	public void setNom_Entreprise(String nom_Entreprise) {
		this.nom_Entreprise = nom_Entreprise;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Employé> getEmployés() {
		return employés;
	}

	public void setEmployés(Set<Employé> employés) {
		this.employés = employés;
	}

	public Entreprise(long id, String nom_Entreprise, String type, Medecin medecin, Secretaire secretaire,
			Set<Employé> employés, Set<Patient> patients) {
		super();
		this.id = id;
		this.nom_Entreprise = nom_Entreprise;
		this.type = type;
		this.medecin = medecin;
		this.secretaire = secretaire;
		this.employés = employés;
		this.patients = patients;
	}
}
