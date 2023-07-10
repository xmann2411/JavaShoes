/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Karla
 */
public class Receipt {
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    //2002-12-01T00:00

    private int id;
    private int KupacID;
    private int ProdavacID;
    private int ParCipelaID;
    //jel moze money iz tablice bit int tu
    private int cijena;
    private int popust;
    private LocalDateTime datumIzdavanja;
    

    public Receipt(int KupacID, int ProdavacID, int ParCipelaID, int cijena, int popust, LocalDateTime datumIzdavanja) {
        this.KupacID = KupacID;
        this.ProdavacID = ProdavacID;
        this.ParCipelaID = ParCipelaID;
        this.cijena = cijena;
        this.popust = popust;
        this.datumIzdavanja = datumIzdavanja;
    }
    
    
    public Receipt(int id, int KupacID, int ProdavacID, int ParCipelaID, int cijena, int popust, LocalDateTime datumIzdavanja) {
        this(KupacID, ProdavacID, ParCipelaID, cijena, popust, datumIzdavanja);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getKupacID() {
        return KupacID;
    }

    public void setKupacID(int KupacID) {
        this.KupacID = KupacID;
    }

    public int getProdavacID() {
        return ProdavacID;
    }

    public void setProdavacID(int ProdavacID) {
        this.ProdavacID = ProdavacID;
    }

    public int getParCipelaID() {
        return ParCipelaID;
    }

    public void setParCipelaID(int ParCipelaID) {
        this.ParCipelaID = ParCipelaID;
    }

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public LocalDateTime getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDateTime datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    
    @Override
    public String toString(){
        return id + " - " + cijena + ", " + datumIzdavanja;
    }


}
