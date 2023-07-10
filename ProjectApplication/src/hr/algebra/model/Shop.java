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
public class Shop {
    
    private int id;
    private String Ime;
    private String Adresa;
    private String Grad;
    private String UShoppingCentru;
    
    public Shop(){
        
    }

    public Shop(String Ime, String Adresa, String Grad, String UShoppingCentru) {
        this.Ime = Ime;
        this.Adresa = Adresa;
        this.Grad = Grad;
        this.UShoppingCentru = UShoppingCentru;
    }
    
    
    public Shop(int id, String Ime, String Adresa, String Grad, String UShoppingCentru) {
        this(Ime, Adresa, Grad, UShoppingCentru);
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

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String Adresa) {
        this.Adresa = Adresa;
    }

    public String getGrad() {
        return Grad;
    }

    public void setGrad(String Grad) {
        this.Grad = Grad;
    }

    public String getUShoppingCentru() {
        return UShoppingCentru;
    }

    public void setUShoppingCentru(String UShoppingCentru) {
        this.UShoppingCentru = UShoppingCentru;
    }
    
    @Override
    public String toString(){
        return id + " - " + Ime;
    }

    
}
