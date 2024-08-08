package project.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entités.Employé;



@Repository
public interface ProviderRepository extends JpaRepository<Employé, Long> {

	Optional<Employé> findByEmail(String email);
	
}