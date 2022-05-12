
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.livreurCN;
import com.mycompany.myapp.service.ServiceLivreur;


/**
 *
 * @author islem
 */
public class AddLivreurForm extends Form{

    public AddLivreurForm(Form previous) {
        setTitle("Add a new livreur");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","nom livreur");
        TextField tfPrenom = new TextField("","prenom livreur");
        TextField tfNumtel= new TextField("","num tel");
      
        Button btnValider = new Button("Add Livreur");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        livreurCN l = new livreurCN ((tfNom.getText().toString()), (tfPrenom.getText().toString()), Integer.parseInt(tfNumtel.getText()));
                    if (new ServiceLivreur().addLivreur(l))
                            Dialog.show("Success","livreur ajouter",new Command("OK"));
                        else Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfPrenom,tfNumtel,btnValider);
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
