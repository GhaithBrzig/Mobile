/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author SBS
 */
public class Repa {
    int id;
    String libProd;
    String description;
    int prixProd;
    int quantite;
    String remise;
    String categorie;
    String path;
    

    public Repa() {
    }

    public Repa(String libProd, String description, String categorie) {
        this.libProd = libProd;
        this.description = description;
        this.categorie = categorie;
    }

    public Repa(String libProd, String description, int prixProd, int quantite, String remise, String categorie) {
        this.libProd = libProd;
        this.description = description;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.remise = remise;
        this.categorie = categorie;
    }

    public Repa(String libProd, String description, int prixProd, int quantite, String remise, String categorie, String path) {
        this.libProd = libProd;
        this.description = description;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.remise = remise;
        this.categorie = categorie;
        this.path = path;
    }

    
    public Repa(int id, String libProd, String description, int prixProd, int quantite, String remise, String categorie) {
        this.id = id;
        this.libProd = libProd;
        this.description = description;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.remise = remise;
        this.categorie = categorie;
    }

    public Repa(int id, String libProd, String description, int prixProd, int quantite, String remise, String categorie, String path) {
        this.id = id;
        this.libProd = libProd;
        this.description = description;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.remise = remise;
        this.categorie = categorie;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibProd() {
        return libProd;
    }

    public void setLibProd(String libProd) {
        this.libProd = libProd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(int prixProd) {
        this.prixProd = prixProd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getRemise() {
        return remise;
    }

    public void setRemise(String remise) {
        this.remise = remise;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Repa{" + "id=" + id + ", libProd=" + libProd + ", description=" + description + ", prixProd=" + prixProd + ", quantite=" + quantite + ", remise=" + remise + ", categorie=" + categorie + ", path=" + path + '}';
    }

  
    
    
}
