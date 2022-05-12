/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author islem
 */
public class HomeForm extends Form {
    Form current;
    public HomeForm(){
    current=this;
    setTitle("Home");
    setLayout(BoxLayout.y());
    
    add(new Label("Choose an option"));
    Button btnAddLivreur = new Button("Add Livreur");
    Button btnListLivreur = new Button("List Livreurs");
    Button btnUpdateLivreur = new Button("Update Livreur");
    Button btnSuppLivreur = new Button("Supprimer Livreur");

    btnAddLivreur.addActionListener(e-> new AddLivreurForm(current).show());
    btnListLivreur.addActionListener(e-> new ListLivreurForm(current).show());
    btnUpdateLivreur.addActionListener(e-> new UpdateLivreurForm(current).show());
    btnSuppLivreur.addActionListener(e-> new SuppLivreur(current).show());
    
  
   // Button btnAddLivraison = new Button("Add Livraison");
    Button btnListLivraison = new Button("List Livraison");
   // Button btnUpdateLivraison = new Button("Update Livraison");
    Button btnSuppLivraison = new Button("Supprimer Livraison");

   // btnAddLivraison.addActionListener(e-> new AddLivreurForm(current).show());
    btnListLivraison.addActionListener(e-> new ListLivraisonForm(current).show());
   // btnUpdateLivraison.addActionListener(e-> new UpdateLivreurForm(current).show());
    btnSuppLivraison.addActionListener(e-> new SuppLivreur(current).show());
    addAll(btnAddLivreur,btnListLivreur,btnUpdateLivreur,btnSuppLivreur,/*btnAddLivraison,*/btnListLivraison,/*btnUpdateLivraison,*/btnSuppLivraison);
    }
}
