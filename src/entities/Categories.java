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
public class Categories {
    int id;
    String categorie;

    public Categories() {
    }

    public Categories(String categorie) {
        this.categorie = categorie;
    }

    public Categories(int id, String categorie) {
        this.id = id;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Categories{" + "id=" + id + ", categorie=" + categorie + '}';
    }
    
}
