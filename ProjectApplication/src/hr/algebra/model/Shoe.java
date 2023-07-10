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
public class Shoe {
    
    private int id;
    private String Marka;
    private String Model;
    private int Broj;
    private String Boja;
    private String OpisProizvoda;
    private String picturePath;

    public Shoe() {
        
    }
    

    public Shoe(String Marka, String Model, int Broj, String Boja, String OpisProizvoda, String picturePath) {
        this.Marka = Marka;
        this.Model = Model;
        this.Broj = Broj;
        this.Boja = Boja;
        this.OpisProizvoda = OpisProizvoda;
        this.picturePath = picturePath;
    }
    
    public Shoe(int id, String Marka, String Model, int Broj, String Boja, String OpisProizvoda, String picturePath) {
        this(Marka, Model, Broj, Boja, OpisProizvoda, picturePath);
        this.id = id;
    }

    public Shoe(String trim, String trim0, String trim1, String trim2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    
    public int getId() {
        return id;
    }

    public String getMarka() {
        return Marka;
    }

    public String getModel() {
        return Model;
    }

    public int getBroj() {
        return Broj;
    }

    public String getBoja() {
        return Boja;
    }

    public String getOpisProizvoda() {
        return OpisProizvoda;
    }

    public void setMarka(String Marka) {
        this.Marka = Marka;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public void setBroj(int Broj) {
        this.Broj = Broj;
    }

    public void setBoja(String Boja) {
        this.Boja = Boja;
    }

    public void setOpisProizvoda(String OpisProizvoda) {
        this.OpisProizvoda = OpisProizvoda;
    }
    
    @Override
    public String toString(){
        return id + " - " + Marka + ", " + Model;
    }
       
}
