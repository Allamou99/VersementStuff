package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CompteService {
    private CompteRepository compteRepository;
    private UtilisateurRepository utilisateurRepository;
    public CompteService(CompteRepository compteRepository,UtilisateurRepository utilisateurRepository){
        this.compteRepository = compteRepository;
        this.utilisateurRepository = utilisateurRepository;
    }
    public Compte findCompteByNumeroCompte(String NrCOmpte) throws CompteNonExistantException {
        return this.compteRepository.findByNrCompte(NrCOmpte)
                .orElseThrow(()->new CompteNonExistantException("No compte found"));
    }
    public Compte findCompteByRib(String rib) throws CompteNonExistantException {
        return this.compteRepository.findByRib(rib)
                .orElseThrow(()->new CompteNonExistantException("No compte found"));
    }
    public void saveCompte(Compte compte){
        this.compteRepository.save(compte);
    }

    public List<Compte> loadAllCompte() {
        List<Compte> all = compteRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }
    public List<Utilisateur> loadAllUtilisateur() {
        List<Utilisateur> all = utilisateurRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }
}
