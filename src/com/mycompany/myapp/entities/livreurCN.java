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
public class livreurCN {
    public int IdLivreur;   

    public int getIdLivreur() {
        return IdLivreur;
    }

    public void setIdLivreur(int IdLivreur) {
        this.IdLivreur = IdLivreur;
    }
    public String nomLivreur;
    public String prenomLivreur;
    public int telLivreur;

    public  livreurCN() {
    }


    public  livreurCN( String NomLivreur, String prenomLivreur, int telLivreur) {
        
        this.nomLivreur = NomLivreur;
        this.prenomLivreur = prenomLivreur;
        this.telLivreur = telLivreur;
    }

  

    public String getNomLivreur() {
        return nomLivreur;
    }

    public void setNomLivreur(String NomLivreur) {
        this.nomLivreur = NomLivreur;
    }

    public String getPrenomLivreur() {
        return prenomLivreur;
    }

    public void setPrenomLivreur(String prenomLivreur) {
        this.prenomLivreur = prenomLivreur;
    }

    public int getTelLivreur() {
        return telLivreur;
    }

    public void setTelLivreur(int telLivreur) {
        this.telLivreur = telLivreur;
    }

    @Override
    public String toString() {
        return "livreur{" + " NomLivreur=" + nomLivreur + ", prenomLivreur=" + prenomLivreur + ", telLivreur=" + telLivreur + '}';
    }
    
    
}
