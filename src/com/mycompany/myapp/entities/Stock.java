/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Stock {

    private int id;
    private int id_categorie;
    private String nom;
    private String unite;
    private int quantite;
    private double prix_unitaire;
    private double prix_total;

    public Stock() {
    }

    public Stock( String nom, String unite, int quantite, double prix_unitaire) {
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.unite = unite;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;

    }

    public Stock(int id, String nom, String unite, int quantite, double prix_unitaire, double prix_total) {
        this.id = id;
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.unite = unite;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;

    }

     public Stock(String id_categorie, String nom, String unite, String quantite,  String prix_unitaire, String prix_total) {
 //To change body of generated methods, choose Tools | Templates.
    }
     
       public Stock(String id,String nom, String unite, String quantite, String prix_unitaire) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    @Override
    public String toString() {
        return "Stockt{" + "id=" + id + ", id_categorie=" + id_categorie + ", nom=" + nom + ", unite=" + unite + ", quantite=" + quantite + ", prix_unitaire=" + prix_unitaire + ", prix_total=" + prix_total + '}';
    }

}

