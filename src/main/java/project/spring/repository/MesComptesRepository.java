package project.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spring.entités.MesComptes;

import java.util.List;

public interface MesComptesRepository extends JpaRepository<MesComptes, Long> {
    List<MesComptes> findByCanal(String canal);
    List<MesComptes> findByDns(String dns); // Nouvelle méthode pour rechercher par DNS
}
