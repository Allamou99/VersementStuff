package ma.octo.assignement.web;

import ma.octo.assignement.dto.VersementDTO;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.service.VersementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versements")
public class VersementController {
    @Autowired
    private VersementService versementService;
    @PostMapping
    public String addVersement(@RequestBody VersementDTO versementDTO) throws CompteNonExistantException {
        this.versementService.addVersement(versementDTO);
        return "Succes";
    }
}
