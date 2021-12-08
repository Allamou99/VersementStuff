package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
class CompteServiceTest {
    private CompteRepository compteRepository = Mockito.mock(CompteRepository.class);
    private UtilisateurRepository utilisateurRepository = Mockito.mock(UtilisateurRepository.class);


    CompteService compteService;
    Compte expectedReturn;
    Compte compte1;
    @BeforeEach
    void Initialization(){
        this.compteService = new CompteService(compteRepository,utilisateurRepository);

        compte1 = new Compte();
        compte1.setNrCompte("010000A000001000");
        compte1.setRib("RIB1");
        compte1.setSolde(BigDecimal.valueOf(200000L));
        compte1.setUtilisateur(null);

        expectedReturn =  new Compte();
        expectedReturn.setNrCompte("010000A000001000");
        expectedReturn.setRib("RIB1");
        expectedReturn.setSolde(BigDecimal.valueOf(200000L));
        expectedReturn.setUtilisateur(null);

    }
    @Test
    @DisplayName("Should find compte by its  NR compte.")
    void findCompteByNumeroCompte() throws CompteNonExistantException {
        when(this.compteRepository.findByNrCompte("010000A000001000"))
                .thenReturn(Optional.of(expectedReturn));
        Compte compteTest = compteService.findCompteByNumeroCompte("010000A000001000");
        assertThat(compteTest).isEqualTo(expectedReturn);
    }

    @Test
    @DisplayName("Should find compte by its RIB.")
    void findCompteByRib() throws CompteNonExistantException {
        when(this.compteRepository.findByRib("RIB1"))
                .thenReturn(Optional.of(expectedReturn));
        Compte compteTest = compteService.findCompteByRib("RIB1");
        assertThat(compteTest).isEqualTo(expectedReturn);
    }
    @DisplayName("Should raise No category with name found exception")
    @Test
    void NoCategoryFindByNameException(){
        assertThatThrownBy(()->{
            when(this.compteRepository.findByNrCompte("010000A000001000"))
                    .thenReturn(Optional.of(expectedReturn));
            compteService.findCompteByNumeroCompte("O554949459598");
        }).isInstanceOf(CompteNonExistantException.class)
                .hasMessage("No compte found");
    }
    @Test
    void saveCompte() {
    }
}