package project.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.spring.entités.MesComptes;
import project.spring.repository.MesComptesRepository;

import java.util.List;

@RestController
@RequestMapping("/api/mesComptes")
public class MesComptesController {

    private final MesComptesRepository comptesRepository;

    @Autowired
    public MesComptesController(MesComptesRepository comptesRepository) {
        this.comptesRepository = comptesRepository;
    }

    @PostMapping("/add")
    public MesComptes addCompte(@RequestBody MesComptes mesComptes) {
        return comptesRepository.save(mesComptes);
    }

    @GetMapping("/all")
    public List<MesComptes> getAllComptes() {
        return comptesRepository.findAll();
    }

    @GetMapping("/canal/{canal}")
    public List<MesComptes> getComptesByCanal(@PathVariable String canal) {
        return comptesRepository.findByCanal(canal);
    }

    @GetMapping("/dns/{dns}") // Exemple pour obtenir des comptes par DNS
    public List<MesComptes> getComptesByDns(@PathVariable String dns) {
        return comptesRepository.findByDns(dns);
    }
}
