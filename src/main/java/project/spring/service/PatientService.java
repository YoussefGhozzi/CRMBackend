package project.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.spring.entités.Patient;
import project.spring.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.orElse(null);
    }

    public Patient addPatient(Patient patient) {
    	  if (patient.getNumDossier() == null) {
              patient.setNumDossier("N/A"); // Définir une valeur par défaut si nécessaire
          }
    	  
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setNom(updatedPatient.getNom());
            patient.setPrenom(updatedPatient.getPrenom());
            patient.setAge(updatedPatient.getAge());
            patient.setTelephone(updatedPatient.getTelephone());
            patient.setAdresse(updatedPatient.getAdresse());
            patient.setNationalite(updatedPatient.getNationalite());
            patient.setCanalTraitement(updatedPatient.getCanalTraitement());
            patient.setSource(updatedPatient.getSource());
            patient.setDateTraitement(updatedPatient.getDateTraitement());
            patient.setDateRDV(updatedPatient.getDateRDV());
            patient.setFacebookLink(updatedPatient.getFacebookLink());
            patient.setWhatsappNum(updatedPatient.getWhatsappNum());
            patient.setNumDossier(updatedPatient.getNumDossier());
            patient.setAdresseMail(updatedPatient.getAdresseMail());
            return patientRepository.save(patient);
        } else {
            return null;
        }
    }

    public boolean deletePatient(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            patientRepository.delete(optionalPatient.get());
            return true;
        } else {
            return false;
        }
    }

    public Patient getPatientByTelephone(String telephone) {
        return patientRepository.findByTelephone(telephone);
    }
 
}
