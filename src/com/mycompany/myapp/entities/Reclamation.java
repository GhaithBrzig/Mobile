/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author ghaith
 */
public class Reclamation {

    private int id;
    private String nomClient;
    private String emailClient;
    private String description;
    private Date date;
    private boolean isSolved;

    public Reclamation() {
    }

    public Reclamation(int id, String nomClient, String emailClient, String description, Date date, boolean isSolved) {
        this.id = id;
        this.nomClient = nomClient;
        this.emailClient = emailClient;
        this.description = description;
        this.date = date;
        this.isSolved = isSolved;
    }

    public Reclamation(String nomClient, String emailClient, String description, boolean isSolved) {
        this.nomClient = nomClient;
        this.emailClient = emailClient;
        this.description = description;
        this.isSolved = isSolved;
    }

    public Reclamation(String nomClient, String emailClient, String description, Date date) {
        this.nomClient = nomClient;
        this.emailClient = emailClient;
        this.description = description;
        this.date = date;

    }
    public Reclamation(int id, boolean isSolved) {
        this.id = id;
        this.isSolved = isSolved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nomClient=" + nomClient + ", emailClient=" + emailClient + ", description=" + description + ", date=" + date + ", isSolved=" + isSolved + '}';
    }

}
