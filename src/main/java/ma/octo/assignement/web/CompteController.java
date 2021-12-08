package ma.octo.assignement.web;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/compteapi/")
public class CompteController {
    @Autowired
    private CompteService compteService;
    @GetMapping("/comptes")
    public List<Compte> loadAllCompte() {
       return this.compteService.loadAllCompte();
    }
    @GetMapping("/utilisateurs")
    public List<Utilisateur> loadAllUsers(){
        return this.compteService.loadAllUtilisateur();
    }
}
