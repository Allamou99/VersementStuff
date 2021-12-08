package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.VirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VirementService {
    private CompteService compteService;
    private VirementRepository virementRepository;
    public VirementService(CompteService compteService, VirementRepository virementRepository){
        this.compteService = compteService;
        this.virementRepository = virementRepository;
    }
    public static final int MONTANT_MAXIMAL = 10000;

    public void checkMontantVirement(BigDecimal montantVirement) throws TransactionException{
        if (montantVirement.intValue() == 0) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (montantVirement.intValue() < 10) {
            System.out.println("Montant minimal de virement non atteint");
            throw new TransactionException("Montant minimal de virement non atteint");
        } else if (montantVirement.intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de virement dépassé");
            throw new TransactionException("Montant maximal de virement dépassé");
        }
    }

    public void checkEmptyMotif(String motif) throws TransactionException{
        if (motif.isEmpty()) {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }
    }
    public void checkTransaction(BigDecimal solde, BigDecimal montantVirement) throws SoldeDisponibleInsuffisantException {
        if (solde.intValue() - montantVirement.intValue() < 0) {
            throw new SoldeDisponibleInsuffisantException("Montant insuffisant");
        }
    }
    public void createVirement(VirementDto virementDto, Compte compteBeneficiaire, Compte compteEmetteur){
        Virement virement = new Virement();
        virement.setDateExecution(virementDto.getDate());
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);
        virement.setMontantVirement(virementDto.getMontantVirement());
        this.virementRepository.save(virement);
    }
    public void updateVirementTransaction(Compte emetteur, Compte beneficiaire, BigDecimal montant){
        emetteur.setSolde(emetteur.getSolde().subtract(montant));
        beneficiaire.setSolde(new BigDecimal(beneficiaire.getSolde().intValue() + montant.intValue()));
        this.compteService.saveCompte(emetteur);
        this.compteService.saveCompte(beneficiaire);
    }
}
