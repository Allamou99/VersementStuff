package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AutiService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.VirementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController(value = "/virements")
@RequestMapping("/virements")
class VirementController {

    //Logger LOGGER = LoggerFactory.getLogger(VirementController.class);

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private VirementRepository virementRepository;
    @Autowired
    private AutiService auditService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private CompteService compteService;
    @Autowired
    private VirementService virementService;
    @GetMapping("lister_virements")
    List<Virement> loadAll() {
        List<Virement> all = virementRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("/lister_comptes")
    List<Compte> loadAllCompte() {
        List<Compte> all = compteRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("/lister_utilisateurs")
    List<Utilisateur> loadAllUtilisateur() {
        List<Utilisateur> all = utilisateurRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @PostMapping("/executerVirements")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTransaction(@RequestBody VirementDto virementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
        Compte compteEmetteur = this.compteService.findCompteByNumeroCompte(virementDto.getNrCompteEmetteur());
        Compte compteBeneficiaire = this.compteService.findCompteByNumeroCompte(virementDto.getNrCompteBeneficiaire());

        this.virementService.checkMontantVirement(virementDto.getMontantVirement());
        this.virementService.checkEmptyMotif(virementDto.getMotif());


        this.virementService.checkTransaction(compteEmetteur.getSolde(),virementDto.getMontantVirement());
        this.virementService.updateVirementTransaction(compteEmetteur, compteBeneficiaire,virementDto.getMontantVirement());


        this.virementService.createVirement(virementDto,compteBeneficiaire,compteEmetteur);

        this.auditService.auditVirement("Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
                        .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement()
                        .toString());
        return "Succes";
    }
}
