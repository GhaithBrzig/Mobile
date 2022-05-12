/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author islemferchichi
 */
public class livraisonCN {
    private int IdLivraison;
    private int IdLivreur;
    private String NomLivreur;
    private String Commande;
    private double FraisdeLivraison;
    
    public livraisonCN() {
    }

    public livraisonCN(int IdLivraison, int IdLivreur, String NomLivreur, String Commande, double FraisdeLivraison) {
        this.IdLivraison = IdLivraison;
        this.IdLivreur = IdLivreur;
        this.NomLivreur = NomLivreur;
        this.FraisdeLivraison = FraisdeLivraison;
        this.Commande = Commande;
    }

    public int getIdLivraison() {
        return IdLivraison;
    }

    public void setIdLivraison(int IdLivraison) {
        this.IdLivraison = IdLivraison;
    }

      public String getCommande() {
        return Commande;
    }

    public void setCommande(String Commande) {
        this.Commande = Commande;
    }

    
    public int getIdLivreur() {
        return IdLivreur;
    }

    public void setIdLivreur(int IdLivreur) {
        this.IdLivreur = IdLivreur;
    }

    public String getNomLivreur() {
        return NomLivreur;
    }

    public void setNomLivreur(String NomLivreur) {
        this.NomLivreur = NomLivreur;
    }

    public double getFraisdeLivraison() {
        return FraisdeLivraison;
    }

    public void setFraisdeLivraison(double FraisdeLivraison) {
        this.FraisdeLivraison = FraisdeLivraison;
    }

    @Override
    public String toString() {
        return "livraison{Livreur=" + NomLivreur + ", FraisdeLivraison=" + FraisdeLivraison + ", Commande=" +Commande + "}";
    }
}