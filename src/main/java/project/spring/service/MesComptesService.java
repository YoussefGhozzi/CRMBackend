package project.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring.entités.MesComptes;
import project.spring.repository.MesComptesRepository;

import java.util.List;

@Service
public class MesComptesService {

    private final MesComptesRepository mesComptesRepository;

    @Autowired
    public MesComptesService(MesComptesRepository mesComptesRepository) {
        this.mesComptesRepository = mesComptesRepository;
    }

    public MesComptes saveMesComptes(MesComptes mesComptes) {
        return mesComptesRepository.save(mesComptes);
    }

    public List<MesComptes> getAllMesComptes() {
        return mesComptesRepository.findAll();
    }

    public List<MesComptes> getMesComptesByCanal(String canal) {
        return mesComptesRepository.findByCanal(canal);
    }

    public List<MesComptes> getMesComptesByDns(String dns) {
        return mesComptesRepository.findByDns(dns);
    }
}
