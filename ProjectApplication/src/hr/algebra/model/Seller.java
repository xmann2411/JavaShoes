/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author Karla
 */
public class Seller {
    
    private int id;
    private String Ime;
    private String Prezime;
    private int GodineStaza;
    private int ProdavaonicaID;
    
    public Seller(){
        
    }

    public Seller(String Ime, String Prezime, int GodineStaza, int ProdavaonicaID) {
        this.Ime = Ime;
        this.Prezime = Prezime;
        this.GodineStaza = GodineStaza;
        this.ProdavaonicaID = ProdavaonicaID;
    }
    
    public Seller(int id, String Ime, String Prezime, int GodineStaza, int ProdavaonicaID) {
        this(Ime, Prezime, GodineStaza, ProdavaonicaID);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String Ime) {
        this.Ime = Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String Prezime) {
        this.Prezime = Prezime;
    }

    public int getGodineStaza() {
        return GodineStaza;
    }

    public void setGodineStaza(int GodineStaza) {
        this.GodineStaza = GodineStaza;
    }

    public int getProdavaonicaID() {
        return ProdavaonicaID;
    }

    public void setProdavaonicaID(int ProdavaonicaID) {
        this.ProdavaonicaID = ProdavaonicaID;
    }
    
    @Override
    public String toString(){
        return id + " - " + Ime + " " + Prezime;
    }

}
