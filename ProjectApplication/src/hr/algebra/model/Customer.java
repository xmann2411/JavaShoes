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
public class Customer {
    
    private int id;
    private String Ime;
    private String Prezime;
    private String OIB;
    private String Email;
    private String brojTelefona;
    private String NazivKartice;
    
    public Customer(){
        
    }

    public Customer(String Ime, String Prezime, String OIB, String Email, String brojTelefona, String NazivKartice) {
        this.Ime = Ime;
        this.Prezime = Prezime;
        this.OIB = OIB;
        this.Email = Email;
        this.brojTelefona = brojTelefona;
        this.NazivKartice = NazivKartice;
    }

    public Customer(int id, String Ime, String Prezime, String OIB, String Email, String brojTelefona, String NazivKartice) {
        this(Ime, Prezime, OIB, Email, brojTelefona, NazivKartice);
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

    public String getOIB() {
        return OIB;
    }

    public void setOIB(String OIB) {
        this.OIB = OIB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getNazivKartice() {
        return NazivKartice;
    }

    public void setNazivKartice(String NazivKartice) {
        this.NazivKartice = NazivKartice;
    }
    
    @Override
    public String toString(){
        return id + " - " + Ime + " " + Prezime;
    }
        
}
