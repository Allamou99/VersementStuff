package ma.octo.assignement.service;

import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.VirementRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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

class VirementServiceTest {
    private VirementService virementService;
    private CompteService compteService = Mockito.mock(CompteService.class);
    private VirementRepository virementRepository = Mockito.mock(VirementRepository.class);
    @BeforeEach
    void Initialization() {
        this.virementService = new VirementService(compteService,virementRepository);
    }
    @Test
    void checkMontantVirement() {
        assertThatThrownBy(()->{
            virementService.checkMontantVirement(BigDecimal.valueOf(0));
        }).isInstanceOf(TransactionException.class)
                .hasMessage("Montant vide");
    }

    @Test
    void checkEmptyMotif() {
    }

    @Test
    void checkTransaction() {
    }

    @Test
    void createVirement() {
    }

    @Test
    void updateVirementTransaction() {
    }
}