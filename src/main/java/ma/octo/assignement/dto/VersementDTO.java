package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VersementDTO {
    private String rib;
    private BigDecimal montant;
    private Date date;
    private String motif;

    public VersementDTO(String rib, BigDecimal montant,String motif) {
        this.rib = rib;
        this.montant = montant;
        this.date = new Date();
        this.motif = motif;
    }

    public String getRib() {
        return rib;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }
    public String getMotif(){
        return motif;
    }
}
