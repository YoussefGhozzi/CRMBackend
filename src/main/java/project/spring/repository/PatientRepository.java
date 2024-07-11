package project.spring.repository;





import project.spring.entités.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	Patient findByTelephone(String telephone);
}
