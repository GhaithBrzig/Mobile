

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ToastBar;
import com.codename1.ui.Form;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceStock;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class AddUpdateReclamationForm extends Form{
 private Reclamation reclamation;

    public AddUpdateReclamationForm(Form previous, boolean add, Reclamation r) {

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 
                    ee -> previous.showBack()
            );
            this.setTitle("Reclamation");

            Container nomClientContainer = new Container(BoxLayout.x());
             Container emailClientContainer = new Container(BoxLayout.x());
            Container descriptionContainer = new Container(BoxLayout.x());
             

            
            
            TextArea descriptionTA = new TextArea("", 10,100);
            descriptionTA.setMaxSize(9999);
            descriptionTA.getAllStyles().setAlignment(TextArea.CENTER);

            TextField nomClientTF = new TextField();
            nomClientTF.getAllStyles().setAlignment(TextArea.CENTER);
            TextField emailClientTF = new TextField();
            emailClientTF.getAllStyles().setAlignment(TextArea.CENTER);
            
            TextField descriptionTF = new TextField();
            descriptionTF.getAllStyles().setAlignment(TextArea.CENTER);
            
        
            
            
            Button submitBtn = new Button("");
           
            nomClientContainer.addAll(new Label("Nom Client: "),nomClientTF);
            emailClientContainer.addAll(new Label("Email Client: "),emailClientTF);
            descriptionContainer.addAll(new Label("Description : "),descriptionTF);

            
            
            if(add)
            {
            submitBtn.setText("Ajouter");
            
           
            
            submitBtn.addActionListener(ee->{
                if(nomClientTF.getText()==""||emailClientTF.getText()==""||descriptionTF.getText()=="")
                {
                    ToastBar.showErrorMessage("Complétez le formulaire");
                    return;
                }
                ServiceReclamation.getInstance().addReclamation(nomClientTF.getText(),emailClientTF.getText(),descriptionTF.getText());
            new ListReclamationsForm().show();
            });
                
              this.addAll(nomClientContainer,emailClientContainer, descriptionContainer, submitBtn);

            }
         
           

            

            this.setLayout(BoxLayout.y());

    }

   
    
}
