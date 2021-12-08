package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDTO;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VersementService {
    @Autowired
    private CompteService compteService;

    public void addVersement(VersementDTO versementDTO) throws CompteNonExistantException {
        Versement versement = new Versement();
        Compte compte = this.compteService.findCompteByRib(versementDTO.getRib());
        compte.setSolde(new BigDecimal(compte.getSolde().intValue() + versementDTO.getMontant().intValue()));

        versement.setMontantVirement(versementDTO.getMontant());
        versement.setMotifVersement(versementDTO.getMotif());
        versement.setCompteBeneficiaire(compte);
        versement.setNom_prenom_emetteur(compte.getUtilisateur().getLastname()+ " "+ compte.getUtilisateur().getFirstname());

        this.compteService.saveCompte(compte);
    }
}
